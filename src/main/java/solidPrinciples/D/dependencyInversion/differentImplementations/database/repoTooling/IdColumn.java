package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.h2Tooling.H2ColumnDefinitionBuilder;

import java.lang.reflect.Field;

public class IdColumn implements ColumnEntry {
    private final Class<?> type;
    private final String value;
    private final H2ColumnDefinitionBuilder h2ColumnDefinitionBuilder;
    private final Field field;

    public IdColumn(Class<?> type, String value, H2ColumnDefinitionBuilder h2ColumnDefinitionBuilder, Field field) {
        this.type = type;
        this.value = value;
        this.h2ColumnDefinitionBuilder = h2ColumnDefinitionBuilder;
        this.field = field;
    }

    @Override
    public String getColumnDefinition() {
        return h2ColumnDefinitionBuilder.toIdColumn(value, type);
    }

    @Override
    public String getColumnName() {
        return value;
    }

    @Override
    public String getFieldName() {
        return field.getName();
    }

    @Override
    public Boolean isId() {
        return true;
    }
}
