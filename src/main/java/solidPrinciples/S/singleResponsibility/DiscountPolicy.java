package solidPrinciples.S.singleResponsibility;

public class DiscountPolicy {
    public Double getDiscountRate(Long id) {
        Double discountRate = 1.0;
        if (id == 3L) {
            discountRate = .85;
        }
        return discountRate;
    }
}