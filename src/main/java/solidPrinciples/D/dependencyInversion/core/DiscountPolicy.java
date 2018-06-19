package solidPrinciples.D.dependencyInversion.core;

public interface DiscountPolicy {
    Double getDiscountRate(Long id);
}
