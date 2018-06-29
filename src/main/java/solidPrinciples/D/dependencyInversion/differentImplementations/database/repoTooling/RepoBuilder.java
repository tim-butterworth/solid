package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.h2Tooling.ConnectionWrapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class RepoBuilder {

    private final ConnectionWrapper connectionWrapper;

    public RepoBuilder(ConnectionWrapper connectionWrapper) {
        this.connectionWrapper = connectionWrapper;
    }

    public <T, I, M extends Repo<T, I>> M build(Class<M> repoClass) {
        Type[] genericInterfaces = repoClass.getGenericInterfaces();
        ParameterizedType genericInterface = (ParameterizedType) genericInterfaces[0];
        Type[] typeArguments = genericInterface.getActualTypeArguments();

        return (M) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{repoClass},
                new RepoInvocationHandler(connectionWrapper, (Class<T>) typeArguments[0])
        );
    }
}
