package solidPrinciples.L.liskovSubstitution.shoppingCartExample;

import java.util.Map;

public class LiskovDiscountPolicy {

    private Map<Long, Double> discountByItemId;

    public LiskovDiscountPolicy(Map<Long, Double> discountByItemId) {
        this.discountByItemId = discountByItemId;
    }

    public Double getDiscountRate(Long id) {
        return discountByItemId.getOrDefault(id, 1.0);
    }
}