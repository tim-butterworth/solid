package solidPrinciples.O.openClosed.justRight;

import initialImplementation.CartAddResult;
import initialImplementation.Item;
import initialImplementation.ShoppingCart;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import solidPrinciples.S.singleResponsibility.Warehouse;

public class OpenClosedShoppingCart implements ShoppingCart {
    private final List<Item> itemList;
    private final Warehouse warehouse;
    private final OpenClosedDiscountPolicy discountPolicy;
    private final OpenClosedTaxPolicy taxPolicy;

    public OpenClosedShoppingCart(
            OpenClosedDiscountPolicy discountPolicy,
            OpenClosedTaxPolicy taxPolicy
    ) {
        itemList = new ArrayList<>();
        warehouse = new Warehouse();
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
