package solidPrinciples.L.liskovSubstitution.shoppingCartExample;

import initialImplementation.CartAddResult;
import initialImplementation.Item;
import initialImplementation.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LiskovShoppingCart implements ShoppingCart {

    private final List<Item> itemList;
    private final LiskovPricing pricing;
    private final LiskovWarehouse warehouse;

    public LiskovShoppingCart(
            LiskovPricing pricing,
            LiskovWarehouse warehouse
    ) {
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
