package solidPrinciples.D.dependencyInversion.differentImplementations.database.h2Tooling;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.ColumnDefinitionBuilder;

import java.util.HashMap;
import java.util.Map;

public class H2ColumnDefinitionBuilder implements ColumnDefinitionBuilder {
    @Override
    public String toRegularColumn(String columnName, Class<?> columnType) {
        return String.format("%s %s", columnName, toH2Type(columnType));
    }

    @Override
    public String toIdColumn(String columnName, Class<?> columnType) {
        return String.format("%s %s PRIMARY KEY auto_increment", columnName, toH2Type(columnType));
    }

    private String toH2Type(Class<?> type) {
        Map<Class<?>, String> h2TypeMap = new HashMap<>();
        h2TypeMap.put(Double.class, "DOUBLE");
        h2TypeMap.put(Long.class, "BIGINT");
        h2TypeMap.put(Integer.class, "INT");

        return h2TypeMap.getOrDefault(type, "VARCHAR");
    }
}
