package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Map;

public class DatabaseRowMapper<T> {

    private final Map<String, Method> columnSetterMap;
    private final InstanceProvider<T> instanceProvider;
    private final ResultSetRowHandler<T> columnMapper;

    public DatabaseRowMapper(
            Map<String, Method> columnSetterMap,
            InstanceProvider instanceProvider, ResultSetRowHandler<T> columnMapper
    ) {
        this.columnSetterMap = columnSetterMap;
        this.instanceProvider = instanceProvider;
        this.columnMapper = columnMapper;
    }

    public T map(ResultSet resultSet) {
        return columnMapper.apply(instanceProvider, columnSetterMap, resultSet);
    }
}
