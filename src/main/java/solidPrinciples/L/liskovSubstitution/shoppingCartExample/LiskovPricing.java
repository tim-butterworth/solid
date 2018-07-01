package solidPrinciples.L.liskovSubstitution.shoppingCartExample;

import initialImplementation.Item;

public class LiskovPricing {

    private final LiskovTaxPolicy taxPolicy;
    private final LiskovDiscountPolicy discountPolicy;

    public LiskovPricing(LiskovDiscountPolicy discountPolicy, LiskovTaxPolicy taxPolicy) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    double getItemPrice(Item item) {
        return (item.getPrice() * discountPolicy.getDiscountRate(item.getId())) * taxPolicy.getTaxRate(item.getType());
    }
}