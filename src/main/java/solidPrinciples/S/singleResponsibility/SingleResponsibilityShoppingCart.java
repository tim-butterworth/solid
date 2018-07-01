package solidPrinciples.S.singleResponsibility;

import initialImplementation.CartAddResult;
import initialImplementation.Item;
import initialImplementation.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SingleResponsibilityShoppingCart implements ShoppingCart {

    private final List<Item> itemList;
    private final Warehouse warehouse;
    private final Pricing pricing;

    public SingleResponsibilityShoppingCart() {
        itemList = new ArrayList<>();
        warehouse = new Warehouse();
        pricing = new Pricing();
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
