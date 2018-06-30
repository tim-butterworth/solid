package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import initialImplementation.ItemType;
import solidPrinciples.D.dependencyInversion.differentImplementations.PersistanceResult;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.h2Tooling.ConnectionWrapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepoInvocationHandler<T, I> implements InvocationHandler {

    private final ConnectionWrapper connectionWrapper;
    private final DatabaseTableModelFactory<T> databaseTableModelFactory = new DatabaseTableModelFactory<>();
    private final DatabaseTableModel<T> databaseTableModel;

    public RepoInvocationHandler(ConnectionWrapper connectionWrapper, Class<T> entityClass) {
        this.connectionWrapper = connectionWrapper;

        databaseTableModel = databaseTableModelFactory.getInstance(entityClass);

        connectionWrapper.execute(tableCreationQuery());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();

        if ("save".equals(methodName)) {
            Boolean executionResult = connectionWrapper.execute(saveQuery((T) args[0], databaseTableModel));

            if (executionResult) return PersistanceResult.SUCCESS;
            return PersistanceResult.FAILURE;
        }
        if ("findById".equals(methodName)) {
            String byIdQuery = findByIdQuery((I) args[0], databaseTableModel);

            return singleResultHandler(byIdQuery);
        }
        if ("getAll".equals(methodName)) {
            return connectionWrapper.execute(findAllQuery(databaseTableModel), databaseTableModel.getRowMapper());
        }
        if (methodName.startsWith("findBy")) {
            String findByColumnName = methodName.replace("findBy", "");

            String query = findByColumnNameQuery(findByColumnName, args[0], databaseTableModel);
            return singleResultHandler(query);
        }
        if ("deleteById".equals(methodName)) {
            String query = deleteByIdQuery((I) args[0], databaseTableModel);
            if(connectionWrapper.execute(query)) return PersistanceResult.SUCCESS;
            else return PersistanceResult.FAILURE;
        }

        throw new RuntimeException("Not implemented " + methodName);
    }

    private String deleteByIdQuery(I id, DatabaseTableModel<T> databaseTableModel) {
        return String.format(
                "DELETE from %s where %s = %s",
                databaseTableModel.getTableName(),
                databaseTableModel.getIDColumn().getColumnName(),
                toH2Value(id)
        );
    }

    private String findByColumnNameQuery(String columnName, Object arg, DatabaseTableModel<T> databaseTableModel) {
        return String
                .format(
                        "SELECT * from %s where %s = %s",
                        databaseTableModel.getTableName(),
                        columnName,
                        toH2Value(arg)
                );
    }

    private String findAllQuery(DatabaseTableModel<T> databaseTableModel) {
        return String.format("SELECT * from %s;", databaseTableModel.getTableName());
    }

    private String tableCreationQuery() {
        String tableName = databaseTableModel.getTableName();
        List<ColumnEntry> columns = databaseTableModel.getColumns();
        String columnString = columns.stream().map(ColumnEntry::getColumnDefinition).collect(Collectors.joining(", "));

        return String.format("DROP TABLE IF EXISTS  %s;", tableName) +
                " " +
                String.format(
                        "CREATE TABLE %s (%s);",
                        tableName,
                        columnString
                );
    }

    private String saveQuery(T dataEntity, DatabaseTableModel<T> databaseTableModel) {
        String tableName = databaseTableModel.getTableName();
        Map<String, Method> columnGetterMap = databaseTableModel.getColumnGetterMap();

        List<Object> values = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        columnGetterMap.entrySet().iterator().forEachRemaining(
                entry -> {
                    try {
                        String columnName = entry.getKey();
                        Optional<Object> columnValue = Optional.ofNullable(entry.getValue().invoke(dataEntity));

                        columnValue.ifPresent(value -> {
                            columns.add(columnName);
                            values.add(value);
                        });
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
        );

        return String.format(
                "INSERT into %s (%s) VALUES(%s)",
                tableName,
                columns.stream().collect(Collectors.joining(", ")),
                values.stream().map(this::toH2Value).collect(Collectors.joining(", "))
        );
    }

    private String findByIdQuery(I id, DatabaseTableModel databaseTableModel) {
        return String.format("SELECT * from %s where %s = %s",
                databaseTableModel.getTableName(),
                databaseTableModel.getIDColumn().getColumnName(),
                toH2Value(id)
        );
    }

    private Optional<Object> singleResultHandler(String byIdQuery) {
        List<T> value = connectionWrapper.execute(byIdQuery, databaseTableModel.getRowMapper());

        if (value.isEmpty()) return Optional.empty();
        return Optional.ofNullable(value.get(0));
    }

    private String toH2Value(Object value) {
        Class<?> aClass = value.getClass();
        if (aClass == String.class) {
            return "'" + value + "'";
        }
        if (aClass == ItemType.class) {
            return "'" + String.valueOf(value) + "'";
        }
        return String.valueOf(value);
    }
}
