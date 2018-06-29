package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

public interface ColumnEntry {
    String getColumnDefinition();
    String getColumnName();
    String getFieldName();
    Boolean isId();
}
