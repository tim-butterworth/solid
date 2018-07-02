package solidPrinciples.I.InterfaceSegregation.externalExamples.proxies;

import solidPrinciples.I.InterfaceSegregation.externalClassWeCanNotChange.TooBigClass;
import java.lang.reflect.Proxy;

public class ProxyProvider {

    public <T> T getInstance(TooBigClass tooBigClass, Class<T> queriesClass) {
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class<?>[]{queriesClass},
                new InterfaceSegregationHandler(tooBigClass)
        );
    }
}
