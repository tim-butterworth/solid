package solidPrinciples.O.openClosed.justRight;

import java.util.Map;

public class OpenClosedDiscountPolicy {

    private Map<Long, Double> discountByItemId;

    public OpenClosedDiscountPolicy(Map<Long, Double> discountByItemId) {
        this.discountByItemId = discountByItemId;
    }

    public Double getDiscountRate(Long id) {
        return discountByItemId.getOrDefault(id, 1.0);
    }
}