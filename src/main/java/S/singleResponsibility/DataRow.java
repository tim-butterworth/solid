package S.singleResponsibility;

import java.util.HashMap;
import java.util.Map;

public class DataRow {
    private Map<String, Object> row = new HashMap<>();

    public void addValue(String column, Object value) {
        row.put(column, value);
    }

    public String getString(String column) {
        return (String) row.get(column);
    }

    public Long getLong(String column) {
        return (Long) row.get(column);
    }
}
