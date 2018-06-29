package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import org.immutables.value.Value;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Value.Immutable
public interface DatabaseTableModel<T> {
    String getTableName();
    List<ColumnEntry> getColumns();
    Map<String, Method> getColumnGetterMap();
    DatabaseRowMapper<T> getRowMapper();
    ColumnEntry getIDColumn();
}
