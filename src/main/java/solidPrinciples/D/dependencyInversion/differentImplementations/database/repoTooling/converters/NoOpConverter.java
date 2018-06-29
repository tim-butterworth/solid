package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.converters;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.Converter;

public class NoOpConverter implements Converter {
    @Override
    public Object apply(Object object) {
        return object;
    }
}
