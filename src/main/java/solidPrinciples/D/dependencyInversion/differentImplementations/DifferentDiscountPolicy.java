package solidPrinciples.D.dependencyInversion.differentImplementations;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.discounts.DatabaseDiscount;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.Repo;

public class DifferentDiscountPolicy {

    private final Repo<DatabaseDiscount, Long> repo;

    public DifferentDiscountPolicy(Repo<DatabaseDiscount, Long> repo) {
        this.repo = repo;
    }

    public Double getDiscountRate(Long id) {
        return repo.getAll().stream()
                .filter(databaseDiscount -> databaseDiscount.getItemId().equals(id))
                .findFirst()
                .map(DatabaseDiscount::getDiscountRate)
                .orElse(1.0);
    }
}
