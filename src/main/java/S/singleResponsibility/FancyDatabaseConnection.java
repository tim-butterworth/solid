package S.singleResponsibility;

import java.util.HashMap;
import java.util.Map;

public class FancyDatabaseConnection {

    private Map<Long, DataRow> database = new HashMap<>();

    public int execute(FancyQuery<Integer> insert) {
        System.out.println(insert);

        simulateDelay();

        return insert.invoke(database);
    }

    public FancyResultSet executeQuery(FancyQuery<FancyResultSet> query) {
        System.out.println(query);

        simulateDelay();

        return query.invoke(database);
    }

    private void simulateDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Map<Long, DataRow> getDatabase() {
        return database;
    }
}
