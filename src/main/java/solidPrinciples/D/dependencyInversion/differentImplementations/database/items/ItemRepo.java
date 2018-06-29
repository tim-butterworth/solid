package solidPrinciples.D.dependencyInversion.differentImplementations.database.items;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.Repo;

import java.util.Optional;

public interface ItemRepo extends Repo<DatabaseDIItem, Long> {
    Optional<DatabaseDIItem> findByITEM_ID(Long itemId);
    Optional<DatabaseDIItem> findByNAME(String name);
}
