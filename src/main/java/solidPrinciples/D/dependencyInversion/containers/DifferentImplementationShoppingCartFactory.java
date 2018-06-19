package solidPrinciples.D.dependencyInversion.containers;

import initialImplementation.ShoppingCart;
import solidPrinciples.D.dependencyInversion.core.DependencyInversionShoppingCart;
import solidPrinciples.D.dependencyInversion.differentImplementations.DifferentDiscountPolicy;
import solidPrinciples.D.dependencyInversion.differentImplementations.DifferentTaxPolicy;
import solidPrinciples.D.dependencyInversion.differentImplementations.DifferentWarehouse;

public class DifferentImplementationShoppingCartFactory {
    public ShoppingCart getInstance() {
        return new DependencyInversionShoppingCart(
                new DifferentWarehouse(),
                new DifferentDiscountPolicy(),
                new DifferentTaxPolicy()
        );
    }
}
