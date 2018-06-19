package solidPrinciples.D.dependencyInversion.differentImplementations;

import solidPrinciples.D.dependencyInversion.core.DiscountPolicy;

public class DifferentDiscountPolicy implements DiscountPolicy {
    @Override
    public Double getDiscountRate(Long id) {
        return null;
    }
}
