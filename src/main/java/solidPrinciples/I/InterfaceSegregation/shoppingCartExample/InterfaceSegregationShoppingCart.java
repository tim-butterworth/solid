package solidPrinciples.I.InterfaceSegregation.shoppingCartExample;

import initialImplementation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InterfaceSegregationShoppingCart implements ShoppingCart {

    private final List<Item> itemList;
    private final ReadWarehouse warehouse;
    private final DiscountPolicy discountPolicy;
    private final TaxPolicy taxPolicy;

    public InterfaceSegregationShoppingCart() {
        itemList = new ArrayList<>();
        warehouse = new FullyFeaturedWarehouse();
        discountPolicy = new DiscountPolicy();
        taxPolicy = new TaxPolicy();
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
