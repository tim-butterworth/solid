package solidPrinciples.D.dependencyInversion.inMemory;

import solidPrinciples.D.dependencyInversion.core.DiscountPolicy;

public class InMemoryDiscountPolicy implements DiscountPolicy {
    @Override
    public Double getDiscountRate(Long id) {
        Double discountRate = 1.0;
        if (id == 3L) {
            discountRate = .85;
        }
        return discountRate;
    }
}