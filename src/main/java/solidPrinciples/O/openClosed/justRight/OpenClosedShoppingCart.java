package solidPrinciples.O.openClosed.justRight;

import initialImplementation.CartAddResult;
import initialImplementation.Item;
import initialImplementation.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OpenClosedShoppingCart implements ShoppingCart {

    private final List<Item> itemList;
    private final OpenClosedPricing openClosedPricing;
    private final OpenClosedWarehouse warehouse;

    public OpenClosedShoppingCart(
            OpenClosedPricing openClosedPricing,
            OpenClosedWarehouse warehouse
    ) {
        itemList = new ArrayList<>();
        this.openClosedPricing = openClosedPricing;
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
                .mapToDouble(openClosedPricing::getItemPrice)
                .sum();
    }

}
