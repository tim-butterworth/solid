package solidPrinciples.D.dependencyInversion.containers;

import initialImplementation.ShoppingCart;
import solidPrinciples.D.dependencyInversion.core.DependencyInversionShoppingCart;
import solidPrinciples.D.dependencyInversion.core.DiscountPolicy;
import solidPrinciples.D.dependencyInversion.core.TaxPolicy;
import solidPrinciples.D.dependencyInversion.core.Warehouse;
import solidPrinciples.D.dependencyInversion.inMemory.InMemoryDiscountPolicy;
import solidPrinciples.D.dependencyInversion.inMemory.InMemoryTaxPolicy;
import solidPrinciples.D.dependencyInversion.inMemory.ListOfItemsWarehouse;

public class InMemoryShoppingCartFactory {
    public ShoppingCart getInstance() {

        Warehouse warehouse = new ListOfItemsWarehouse();
        DiscountPolicy discountPolicy = new InMemoryDiscountPolicy();
        TaxPolicy taxPolicy = new InMemoryTaxPolicy();

        return new DependencyInversionShoppingCart(
                warehouse,
                discountPolicy,
                taxPolicy
        );
    }
}
