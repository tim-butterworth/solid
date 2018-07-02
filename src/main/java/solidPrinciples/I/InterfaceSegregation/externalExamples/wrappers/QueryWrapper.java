package solidPrinciples.I.InterfaceSegregation.externalExamples.wrappers;

import solidPrinciples.I.InterfaceSegregation.externalClassWeCanNotChange.TooBigClass;
import solidPrinciples.I.InterfaceSegregation.externalExamples.Queries;

import java.math.BigInteger;

public class QueryWrapper implements Queries {

    private final TooBigClass tooBigClass;

    public QueryWrapper(TooBigClass tooBigClass) {
        this.tooBigClass = tooBigClass;
    }

    @Override
    public String query1(Double arg) {
        return tooBigClass.query1(arg);
    }

    @Override
    public String query2(Long arg) {
        return tooBigClass.query2(arg);
    }

    @Override
    public String query3(BigInteger arg) {
        return tooBigClass.query3(arg);
    }
}
