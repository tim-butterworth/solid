package solidPrinciples.D.dependencyInversion.differentImplementations.database.discounts;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.Repo;

public interface DiscountRepo extends Repo<DatabaseDiscount, Long> {
}
