package solidPrinciples.D.dependencyInversion.inMemory;

import initialImplementation.Item;
import solidPrinciples.D.dependencyInversion.core.DiscountPolicy;

public class InMemoryDiscountPolicy implements DiscountPolicy {
    @Override
    public Double getDiscountRate(Item item) {
        Double discountRate = 1.0;
        if (item.getId() == 3L) {
            discountRate = .85;
        }
        return discountRate;
    }
}