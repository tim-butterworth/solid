package solidPrinciples.O.openClosed.tooClosed;

public class TooClosed {
    public String execute() {
        int value = 0;
        while(value<10) {
            value++;
        }
        return String.valueOf(value);
    }
}
