package solidPrinciples.D.dependencyInversion.differentImplementations.database.h2Tooling;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.DatabaseRowMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConnectionWrapper {
    public Boolean execute(String query) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            return conn.createStatement().execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> execute(String query, DatabaseRowMapper<T> mapper) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            ResultSet resultSet = conn.createStatement().executeQuery(query);

            List<T> results = new LinkedList<>();
            while(resultSet.next()) {
                results.add(mapper.map(resultSet));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
