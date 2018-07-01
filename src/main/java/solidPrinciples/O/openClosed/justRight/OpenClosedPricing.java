package solidPrinciples.O.openClosed.justRight;

import initialImplementation.Item;

public class OpenClosedPricing {

    private final OpenClosedTaxPolicy taxPolicy;
    private final OpenClosedDiscountPolicy discountPolicy;

    public OpenClosedPricing(OpenClosedDiscountPolicy discountPolicy, OpenClosedTaxPolicy taxPolicy) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    double getItemPrice(Item item) {
        return (item.getPrice() * discountPolicy.getDiscountRate(item.getId())) * taxPolicy.getTaxRate(item.getType());
    }
}