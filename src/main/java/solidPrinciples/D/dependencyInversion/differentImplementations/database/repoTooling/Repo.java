package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling;

import solidPrinciples.D.dependencyInversion.differentImplementations.PersistanceResult;

import java.util.Collection;
import java.util.Optional;

public interface Repo<T, I> {
    Collection<T> getAll();
    Optional<T> findById(I id);

    PersistanceResult deleteById(I id);
    PersistanceResult save(T data);
}
