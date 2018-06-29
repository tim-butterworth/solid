package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

public interface ColumnDefinitionBuilder {
    String toIdColumn(String columnName, Class<?> columnType);
    String toRegularColumn(String columnName, Class<?> columnType);
}
