package solidPrinciples.D.dependencyInversion.inMemory;

import initialImplementation.Item;
import solidPrinciples.D.dependencyInversion.core.Pricing;

public class InMemoryPricing implements Pricing {

    private final InMemoryDiscountPolicy discountPolicy;
    private final InMemoryTaxPolicy taxPolicy;

    public InMemoryPricing(InMemoryDiscountPolicy discountPolicy, InMemoryTaxPolicy taxPolicy) {
        this.discountPolicy = discountPolicy;
        this.taxPolicy = taxPolicy;
    }

    @Override
    public double getItemPrice(Item item) {
        return (item.getPrice() * discountPolicy.getDiscountRate(item.getId())) * taxPolicy.getTaxRate(item.getType());
    }
}
