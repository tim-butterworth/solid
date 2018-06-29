package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.h2Tooling.H2ColumnDefinitionBuilder;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseColumn;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseID;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseTable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class DatabaseTableModelFactory<T> {
    public DatabaseTableModel<T> getInstance(Class<T> entityClass) {
        DatabaseTable annotation = entityClass.getAnnotation(DatabaseTable.class);

        String tableName = "";
        if (annotation != null) {
            tableName = annotation.value();
        }

        List<ColumnEntry> columns = new ArrayList<>();

        Arrays.asList(entityClass.getDeclaredFields()).forEach(field -> {
            DatabaseColumn databaseColumn = field.getAnnotation(DatabaseColumn.class);
            DatabaseID idAnnotation = field.getAnnotation(DatabaseID.class);

            Class<?> type = field.getType();
            H2ColumnDefinitionBuilder h2ColumnDefinitionBuilder = new H2ColumnDefinitionBuilder();

            if (databaseColumn != null) {
                ColumnEntry columnEntry = makeColumn(RegularColumn.class, field, type, h2ColumnDefinitionBuilder, databaseColumn.value());
                columns.add(columnEntry);
            }

            if (idAnnotation != null) {
                ColumnEntry columnEntry = makeColumn(IdColumn.class, field, type, h2ColumnDefinitionBuilder, idAnnotation.value());
                columns.add(columnEntry);
            }
        });

        Map<String, String> columnNameGetterName = getMethodMap(columns, "get");
        Map<String, String> columnNameSetterName = getMethodMap(columns, "set");

        Method[] methods = entityClass.getDeclaredMethods();

        Map<String, Method> columnGetterMap = getQualifiedMethodMap(columnNameGetterName, methods, "get");
        Map<String, Method> columnSetterMap = getQualifiedMethodMap(columnNameSetterName, methods, "set");

        DatabaseRowMapper<T> tDatabaseRowMapper = new DatabaseRowMapper<>(
                columnSetterMap,
                new InstanceProvider<>(entityClass),
                new ResultSetRowHandler<>()
        );

        ColumnEntry idColumn = columns.stream()
                .filter(ColumnEntry::isId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There was no ID column found, please mark a column with @DatabaseID('id column name')"));

        return ImmutableDatabaseTableModel.<T>builder()
                .columns(columns)
                .tableName(tableName)
                .columnGetterMap(columnGetterMap)
                .rowMapper(tDatabaseRowMapper)
                .iDColumn(idColumn)
                .build();
    }

    private ColumnEntry makeColumn(Class<? extends ColumnEntry> columnClass, Field field, Class<?> type, H2ColumnDefinitionBuilder h2ColumnDefinitionBuilder, String value) {
        try {
            Constructor<?> constructor = columnClass.getDeclaredConstructors()[0];
            return (ColumnEntry) constructor.newInstance(
                    type,
                    value,
                    h2ColumnDefinitionBuilder,
                    field
            );
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Method> getQualifiedMethodMap(Map<String, String> columnNameGetterName, Method[] methods, String methodQualifier) {
        Map<String, Method> columnGetterMap = new HashMap<>();
        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith(methodQualifier))
                .forEach(getter -> {
                    String name = getter.getName();

                    Optional.ofNullable(columnNameGetterName.get(name.toLowerCase()))
                            .ifPresent(column -> columnGetterMap.put(column, getter));
                });
        return columnGetterMap;
    }

    private Map<String, String> getMethodMap(List<ColumnEntry> columns, String methodQualifier) {
        return columns.stream().collect(Collectors.toMap(
                (ColumnEntry columnEntry) -> methodQualifier + columnEntry.getFieldName().toLowerCase(),
                ColumnEntry::getColumnName
        ));
    }
}