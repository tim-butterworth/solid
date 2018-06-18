package solidPrinciples.D.dependencyInversion.core;

import initialImplementation.Item;

public interface DiscountPolicy {
    Double getDiscountRate(Item item);
}
