package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

@FunctionalInterface
public interface Converter {
    Object apply(Object object);
}
