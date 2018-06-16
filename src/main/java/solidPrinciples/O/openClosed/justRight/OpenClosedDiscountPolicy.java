package solidPrinciples.O.openClosed.justRight;

import initialImplementation.Item;

import java.util.Map;

public class OpenClosedDiscountPolicy {

    private Map<Long, Double> discountByItemId;

    public OpenClosedDiscountPolicy(Map<Long, Double> discountByItemId) {
        this.discountByItemId = discountByItemId;
    }

    public Double getDiscountRate(Item item) {
        return discountByItemId.getOrDefault(item.getId(), 1.0);
    }
}