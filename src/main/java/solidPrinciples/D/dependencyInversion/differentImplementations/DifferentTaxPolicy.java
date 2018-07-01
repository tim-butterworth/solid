package solidPrinciples.D.dependencyInversion.differentImplementations;

import initialImplementation.ItemType;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.Repo;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.taxes.DatabaseTax;

public class DifferentTaxPolicy {

    private final Repo<DatabaseTax, Long> repo;

    public DifferentTaxPolicy(Repo<DatabaseTax, Long> repo) {
        this.repo = repo;
    }

    public Double getTaxRate(ItemType type) {
        return repo.getAll().stream()
                .filter(databaseTax -> databaseTax.getItemType() == type).findFirst()
                .map(DatabaseTax::getTaxRate)
                .orElse(1.0D);
    }
}
