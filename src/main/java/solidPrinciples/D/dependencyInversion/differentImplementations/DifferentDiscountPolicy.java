package solidPrinciples.D.dependencyInversion.differentImplementations;

import solidPrinciples.D.dependencyInversion.core.DiscountPolicy;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.discounts.DatabaseDiscount;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.Repo;

public class DifferentDiscountPolicy implements DiscountPolicy {

    private final Repo<DatabaseDiscount, Long> repo;

    public DifferentDiscountPolicy(Repo<DatabaseDiscount, Long> repo) {
        this.repo = repo;
    }

    @Override
    public Double getDiscountRate(Long id) {
        return repo.getAll().stream()
                .filter(databaseDiscount -> databaseDiscount.getItemId().equals(id))
                .findFirst()
                .map(DatabaseDiscount::getDiscountRate)
                .orElse(1.0);
    }
}
