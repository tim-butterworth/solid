package solidPrinciples.I.InterfaceSegregation.externalClassWeCanNotChange;

import java.math.BigInteger;

public class TooBigClass {
    public void command1() {
        System.out.println("Doing command1");
    }

    public void command2() {
        System.out.println("Doing command2");
    }

    public void command3() {
        System.out.println("Doing command3");
    }

    public String query1(Double arg) {
        return "query1 results in this much: " + String.valueOf(arg);
    }

    public String query2(Long arg) {
        return "query2 results in this much: " + String.valueOf(arg);
    }

    public String query3(BigInteger arg) {
        return "query3 results in this much: " + String.valueOf(arg);
    }
}
