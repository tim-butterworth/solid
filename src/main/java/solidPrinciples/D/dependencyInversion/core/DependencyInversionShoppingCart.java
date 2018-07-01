package solidPrinciples.D.dependencyInversion.core;

import initialImplementation.CartAddResult;
import initialImplementation.Item;
import initialImplementation.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DependencyInversionShoppingCart implements ShoppingCart {

    private final List<Item> itemList;
    private final Warehouse warehouse;
    private final Pricing pricing;

    public DependencyInversionShoppingCart(Warehouse warehouse, Pricing pricing) {
        itemList = new ArrayList<>();
        this.pricing = pricing;
        this.warehouse = warehouse;
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
        return itemList.stream()
                .mapToDouble(pricing::getItemPrice)
                .sum();
    }
}
