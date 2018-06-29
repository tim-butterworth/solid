package solidPrinciples.I.InterfaceSegregation.shoppingCartExample;

import initialImplementation.CartAddResult;
import initialImplementation.Item;
import initialImplementation.ShoppingCart;
import solidPrinciples.O.openClosed.justRight.OpenClosedDiscountPolicy;
import solidPrinciples.O.openClosed.justRight.OpenClosedTaxPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InterfaceSegregationShoppingCart implements ShoppingCart {

    private final List<Item> itemList;
    private final ReadWarehouse warehouse;
    private final OpenClosedDiscountPolicy discountPolicy;
    private final OpenClosedTaxPolicy taxPolicy;

    public InterfaceSegregationShoppingCart(
            OpenClosedDiscountPolicy discountPolicy,
            OpenClosedTaxPolicy taxPolicy,
            ReadWarehouse warehouse
    ) {
        this.discountPolicy = discountPolicy;
        this.taxPolicy = taxPolicy;
        this.warehouse = warehouse;
        itemList = new ArrayList<>();
    }

    @Override
    public CartAddResult addItem(Long itemId) {
        Optional<Item> optionalItem = warehouse.getItem(itemId);

        // You can still do this...
//        FullyFeaturedWarehouse fullyFeaturedWarehouse = (FullyFeaturedWarehouse) warehouse;
//        fullyFeaturedWarehouse.addItem(ImmutableItem.builder()
//                .id(5L)
//                .description("Item added to warehouse from shopping cart")
//                .name("Should not be allowed to do this")
//                .price(100D)
//                .type(ItemType.ESSENTIAL)
//                .build()
//        );

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
