package solidPrinciples.S.singleResponsibility;

import initialImplementation.CartAddResult;
import initialImplementation.Item;
import initialImplementation.ShoppingCart;

import java.util.*;

public class SingleResponsibilityShoppingCart implements ShoppingCart {

    private final List<Item> itemList;
    private final Warehouse warehouse;
    private final DiscountPolicy discountPolicy;
    private final TaxPolicy taxPolicy;

    public SingleResponsibilityShoppingCart() {
        itemList = new ArrayList<>();
        warehouse = new Warehouse();
        discountPolicy = new DiscountPolicy();
        taxPolicy = new TaxPolicy();
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
            total = total + ((price * discountPolicy.getDiscountRate(item.getId())) * taxPolicy.getTaxRate(item.getType()));
        }

        return total;
    }

}
