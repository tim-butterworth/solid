package solidPrinciples.D.dependencyInversion.containers;

import initialImplementation.ShoppingCart;
import solidPrinciples.D.dependencyInversion.differentImplementations.BetterDiscountPolicy;
import solidPrinciples.D.dependencyInversion.differentImplementations.BetterTaxPolicy;
import solidPrinciples.D.dependencyInversion.differentImplementations.BetterWarehouse;
import solidPrinciples.D.dependencyInversion.core.DependencyInversionShoppingCart;

public class DifferentImplementationShoppingCartFactory {
    public ShoppingCart getInstance() {
        return new DependencyInversionShoppingCart(
                new BetterWarehouse(),
                new BetterDiscountPolicy(),
                new BetterTaxPolicy()
        );
    }
}
