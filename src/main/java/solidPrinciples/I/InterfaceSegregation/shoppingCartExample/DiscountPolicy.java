package solidPrinciples.I.InterfaceSegregation.shoppingCartExample;

import initialImplementation.Item;

public class DiscountPolicy {
    public Double getDiscountRate(Item item) {
        Double discountRate = 1.0;
        if (item.getId() == 3L) {
            discountRate = .85;
        }
        return discountRate;
    }
}