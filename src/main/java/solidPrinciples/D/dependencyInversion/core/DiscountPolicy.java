package solidPrinciples.D.dependencyInversion.core;

import initialImplementation.Item;

public interface DiscountPolicy {
    Double getDiscountRate(Long id);
}
