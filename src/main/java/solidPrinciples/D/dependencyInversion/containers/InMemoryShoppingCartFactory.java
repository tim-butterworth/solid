package solidPrinciples.D.dependencyInversion.containers;

import initialImplementation.ShoppingCart;
import solidPrinciples.D.dependencyInversion.core.DependencyInversionShoppingCart;
import solidPrinciples.D.dependencyInversion.core.Warehouse;
import solidPrinciples.D.dependencyInversion.inMemory.InMemoryDiscountPolicy;
import solidPrinciples.D.dependencyInversion.inMemory.InMemoryPricing;
import solidPrinciples.D.dependencyInversion.inMemory.InMemoryTaxPolicy;
import solidPrinciples.D.dependencyInversion.inMemory.ListOfItemsWarehouse;

public class InMemoryShoppingCartFactory {
    public ShoppingCart getInstance() {

        Warehouse warehouse = new ListOfItemsWarehouse();
        InMemoryDiscountPolicy discountPolicy = new InMemoryDiscountPolicy();
        InMemoryTaxPolicy taxPolicy = new InMemoryTaxPolicy();

        return new DependencyInversionShoppingCart(
                warehouse,
                new InMemoryPricing(
                        discountPolicy,
                        taxPolicy
                )
        );
    }
}
