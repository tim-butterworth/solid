package S.singleResponsibility;

public class FancyResultSet {

    private boolean nextWasCalled = false;
    private DataRow[] row;
    private int pointer = -1;

    public FancyResultSet(DataRow... row) {
        this.row = row;
    }

    public void next() {
        nextWasCalled = true;
        pointer++;
    }

    public String getString(String column) {
        blowUpIfNextWasNotCalled();

        return row[pointer].getString(column);
    }

    public Long getLong(String column) {
        blowUpIfNextWasNotCalled();

        return row[pointer].getLong(column);
    }

    public Boolean hasNext() {
        return pointer < row.length && row.length > 0;
    }

    private void blowUpIfNextWasNotCalled() {
        if (!nextWasCalled) {
            throw new RuntimeException("Why would you think it would work that way? I blow up if you don't call next!");
        }
    }
}
