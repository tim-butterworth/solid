package S.singleResponsibility;

import java.util.Collection;
import java.util.Optional;

public class MultiPurposeModel {

    private static final String TABLE_NAME = "MODEL";
    private static FancyDatabaseConnection fancyDatabaseConnection;
    private String modelStringAttribute;
    private Long modelId;

    public String getModelStringAttribute() {
        return Optional
                .ofNullable(modelStringAttribute)
                .orElse("N/A");
    }

    public void setModelStringAttribute(String modelStringAttribute) {
        this.modelStringAttribute = modelStringAttribute;
    }

    public Long getModelId() {
        return Optional
                .ofNullable(modelId)
                .orElse(-1L);
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public PersistenceResult save() {
        FancyDatabaseConnection databaseConnection = getDatabaseConnection();

        FancyResultSet fancyResultSet = databaseConnection.executeQuery(getSelectQuery());

        FancyQuery<Integer> query;
        if (fancyResultSet.hasNext()) {
            query = getUpdateQuery();
        } else {
            query = getInsertQuery();
        }

        int i = databaseConnection.execute(query);
        if (i == 1) {
            return PersistenceResult.SUCCESS;
        }
        return PersistenceResult.FAILURE;
    }

    private FancyQuery<FancyResultSet> getSelectQuery() {
        return database -> Optional.ofNullable(database.get(getModelId()))
                .map(FancyResultSet::new)
                .orElseGet(FancyResultSet::new);
    }

    private FancyQuery<Integer> getUpdateQuery() {
        return database -> {
            DataRow dataRow = database.get(getModelId());

            if (dataRow == null) throw new RuntimeException("Can't update a record that does not exist");

            database.put(getModelId(), getDataRow());

            return 1;
        };
    }

    private FancyQuery<Integer> getInsertQuery() {
        return database -> {
            DataRow dataRow = database.get(getModelId());

            if (dataRow == null) {
                database.put(getModelId(), getDataRow());

                return 1;
            }

            throw new RuntimeException("Can't insert a record with an existing id");
        };
    }

    private DataRow getDataRow() {
        DataRow dataRow = new DataRow();
        dataRow.addValue("id", getModelId());
        dataRow.addValue("stringAttribute", getModelStringAttribute());
        return dataRow;
    }

    private static FancyDatabaseConnection getDatabaseConnection() {
        fancyDatabaseConnection = Optional
                .ofNullable(fancyDatabaseConnection)
                .orElseGet(FancyDatabaseConnection::new);

        return fancyDatabaseConnection;
    }

    public Collection<DataRow> selectAll() {
        return fancyDatabaseConnection.getDatabase().values();
    }
}
