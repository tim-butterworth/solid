package solidPrinciples.D.dependencyInversion.core;

import initialImplementation.CartAddResult;
import initialImplementation.Item;
import initialImplementation.ShoppingCart;
import solidPrinciples.D.dependencyInversion.inMemory.InMemoryTaxPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DependencyInversionShoppingCart implements ShoppingCart {

    private final List<Item> itemList;
    private final Warehouse warehouse;
    private final DiscountPolicy discountPolicy;
    private final TaxPolicy taxPolicy;

    public DependencyInversionShoppingCart(
            Warehouse warehouse,
            DiscountPolicy discountPolicy,
            TaxPolicy taxPolicy
    ) {
        itemList = new ArrayList<>();
        this.warehouse = warehouse;
        this.discountPolicy = discountPolicy;
        this.taxPolicy = taxPolicy;
    }

    @Override
    public CartAddResult addItem(Long itemId) {
        Optional<Item> optionalItem = warehouse.getItem(itemId);

        optionalItem.ifPresent(itemList::add);

        return optionalItem
                .map(i -> CartAddResult.SUCCESS)
                .orElse(CartAddResult.FAILURE);
    }

    @Override
    public Double calculateBill() {
        Double total = 0D;

        for (Item item : itemList) {
            Double price = item.getPrice();
            total = total + ((price * discountPolicy.getDiscountRate(item)) * taxPolicy.getTaxRate(item));
        }

        return total;
    }

}
