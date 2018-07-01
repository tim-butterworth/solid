package solidPrinciples.S.singleResponsibility;

import initialImplementation.Item;

public class Pricing {

    private final DiscountPolicy discountPolicy;
    private final TaxPolicy taxPolicy;

    public Pricing() {
        taxPolicy = new TaxPolicy();
        discountPolicy = new DiscountPolicy();
    }

    double getItemPrice(Item item) {
        return (item.getPrice() * discountPolicy.getDiscountRate(item.getId())) * taxPolicy.getTaxRate(item.getType());
    }
}