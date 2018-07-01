package solidPrinciples.D.dependencyInversion.differentImplementations;

import initialImplementation.Item;
import solidPrinciples.D.dependencyInversion.core.Pricing;

public class DifferentPricing implements Pricing {

    private final DifferentTaxPolicy taxPolicy;
    private final DifferentDiscountPolicy discountPolicy;

    public DifferentPricing(DifferentTaxPolicy taxPolicy, DifferentDiscountPolicy discountPolicy) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    public double getItemPrice(Item item) {
        return (item.getPrice() * discountPolicy.getDiscountRate(item.getId())) * taxPolicy.getTaxRate(item.getType());
    }
}
