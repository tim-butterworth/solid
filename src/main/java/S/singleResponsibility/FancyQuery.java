package S.singleResponsibility;

import java.util.Map;

public interface FancyQuery<T> {
    T invoke(Map<Long, DataRow> database);
}
