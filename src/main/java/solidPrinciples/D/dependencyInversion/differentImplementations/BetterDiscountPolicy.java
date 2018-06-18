package solidPrinciples.D.dependencyInversion.differentImplementations;

import initialImplementation.Item;
import solidPrinciples.D.dependencyInversion.core.DiscountPolicy;

public class BetterDiscountPolicy implements DiscountPolicy {
    @Override
    public Double getDiscountRate(Item item) {
        return null;
    }
}
