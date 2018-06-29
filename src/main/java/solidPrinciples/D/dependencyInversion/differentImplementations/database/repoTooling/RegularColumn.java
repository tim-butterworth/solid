package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import java.lang.reflect.Field;

public class RegularColumn implements ColumnEntry {
    private final Class<?> type;
    private final String value;
    private final ColumnDefinitionBuilder columnDefinitionBuilder;
    private final Field field;

    public RegularColumn(Class<?> type, String value, ColumnDefinitionBuilder columnDefinitionBuilder, Field field) {
        this.type = type;
        this.value = value;
        this.columnDefinitionBuilder = columnDefinitionBuilder;
        this.field = field;
    }

    @Override
    public String getColumnDefinition() {
        return this.columnDefinitionBuilder.toRegularColumn(value, type);
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
        return false;
    }
}
