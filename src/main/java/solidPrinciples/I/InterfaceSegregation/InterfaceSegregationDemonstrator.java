package solidPrinciples.I.InterfaceSegregation;

import solidPrinciples.I.InterfaceSegregation.externalClassWeCanNotChange.TooBigClass;
import solidPrinciples.I.InterfaceSegregation.externalExamples.Commands;
import solidPrinciples.I.InterfaceSegregation.externalExamples.Queries;
import solidPrinciples.I.InterfaceSegregation.externalExamples.proxies.ProxyProvider;
import solidPrinciples.I.InterfaceSegregation.externalExamples.wrappers.CommandWrapper;
import solidPrinciples.I.InterfaceSegregation.externalExamples.wrappers.QueryWrapper;

import java.math.BigInteger;

public class InterfaceSegregationDemonstrator {
    public static void main(String[] args) {
        TooBigClass tooBigClass = new TooBigClass();
        ProxyProvider proxyProvider = new ProxyProvider();

        System.out.println("Hand crafted wrapper implementation");
        exerciseCommandsImplementation(new CommandWrapper(tooBigClass));
        exerciseQueriesImplementation(new QueryWrapper(tooBigClass));

        System.out.println();
        System.out.println("Proxy wrapper implementation");
        exerciseCommandsImplementation(proxyProvider.getInstance(tooBigClass, Commands.class));
        exerciseQueriesImplementation(proxyProvider.getInstance(tooBigClass, Queries.class));
    }

    private static void exerciseCommandsImplementation(Commands commandWrapper) {
        commandWrapper.command1();
        commandWrapper.command2();
        commandWrapper.command3();
    }

    private static void exerciseQueriesImplementation(Queries queryWrapper) {
        String query1 = queryWrapper.query1(10D);
        String query2 = queryWrapper.query2(101L);
        String query3 = queryWrapper.query3(new BigInteger("10000000001"));

        System.out.println(query1);
        System.out.println(query2);
        System.out.println(query3);
    }
}
