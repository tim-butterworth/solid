package solidPrinciples.D.dependencyInversion.differentImplementations.database.taxes;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.Repo;

public interface TaxRepo extends Repo<DatabaseTax, Long> {
}
