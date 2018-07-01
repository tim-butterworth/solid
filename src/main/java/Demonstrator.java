import initialImplementation.*;
import solidPrinciples.D.dependencyInversion.containers.DifferentImplementationShoppingCartFactory;
import solidPrinciples.D.dependencyInversion.containers.InMemoryShoppingCartFactory;
import solidPrinciples.I.InterfaceSegregation.shoppingCartExample.FullyFeaturedWarehouse;
import solidPrinciples.I.InterfaceSegregation.shoppingCartExample.InterfaceSegregationShoppingCart;
import solidPrinciples.O.openClosed.justRight.*;
import solidPrinciples.S.singleResponsibility.SingleResponsibilityShoppingCart;

import java.util.*;

import static java.util.Arrays.asList;

public class Demonstrator {

    public static void main(String[] args) {
        ShoppingCart initialShoppingCart = new InitialShoppingCart();
        ShoppingCart singleResponsibilityShoppingCart = new SingleResponsibilityShoppingCart();
        ShoppingCart openClosedShoppingCart = getOpenClosedShoppingCart();
        ShoppingCart interfaceSegregationShoppingCart = getInterfaceSegregationShoppingCart();

        List<ShoppingCart> shoppingCarts = Arrays.asList(
                initialShoppingCart,
                singleResponsibilityShoppingCart,
                openClosedShoppingCart,
                interfaceSegregationShoppingCart,
                new InMemoryShoppingCartFactory().getInstance(),
                new DifferentImplementationShoppingCartFactory().getInstance()
        );

        shoppingCarts.forEach(shoppingCart -> {
            shoppingCart.addItem(1L);
            shoppingCart.addItem(1L);
            shoppingCart.addItem(1L);
            shoppingCart.addItem(1L);
            shoppingCart.addItem(1L);
            shoppingCart.addItem(2L);
            shoppingCart.addItem(2L);
            shoppingCart.addItem(2L);
            shoppingCart.addItem(4L);
            shoppingCart.addItem(9L);

            System.out.println(shoppingCart.getClass().getSimpleName() + " -> " + shoppingCart.calculateBill());
        });
    }

    private static ShoppingCart getInterfaceSegregationShoppingCart() {
        Map<Long, List<Item>> warehouseItems = getWarehouseItems();

        FullyFeaturedWarehouse warehouse = new FullyFeaturedWarehouse();
        warehouseItems.entrySet().iterator().forEachRemaining(entry -> entry.getValue().forEach(warehouse::addItem));

        return new InterfaceSegregationShoppingCart(
                getOpenClosedDiscountPolicy(),
                getOpenClosedTaxPolicy(),
                warehouse
        );
    }

    private static ShoppingCart getOpenClosedShoppingCart() {
        OpenClosedDiscountPolicy discountPolicy = getOpenClosedDiscountPolicy();
        OpenClosedTaxPolicy taxPolicy = getOpenClosedTaxPolicy();

        Map<Long, List<Item>> map = getWarehouseItems();
        OpenClosedWarehouse warehouse = new OpenClosedWarehouse(map);

        return new OpenClosedShoppingCart(
                new OpenClosedPricing(
                        discountPolicy,
                        taxPolicy
                ),
                warehouse
        );
    }

    private static OpenClosedTaxPolicy getOpenClosedTaxPolicy() {
        Map<ItemType, Double> itemTypeToTaxRate = new HashMap<>();
        itemTypeToTaxRate.put(ItemType.ESSENTIAL, 1.0);
        itemTypeToTaxRate.put(ItemType.JUST_RIGHT, 1.08);
        itemTypeToTaxRate.put(ItemType.LUXURY, 1.15);
        return new OpenClosedTaxPolicy(itemTypeToTaxRate);
    }

    private static OpenClosedDiscountPolicy getOpenClosedDiscountPolicy() {
        Map<Long, Double> itemIdToDiscountMap = new HashMap<>();
        itemIdToDiscountMap.put(3L, .85);
        return new OpenClosedDiscountPolicy(itemIdToDiscountMap);
    }

    private static Map<Long, List<Item>> getWarehouseItems() {
        Map<Long, List<Item>> map = new HashMap<>();

        Item soup = ImmutableItem.builder()
                .description("Some fine soup")
                .name("Can of Soup")
                .type(ItemType.ESSENTIAL)
                .price(.99)
                .id(1L)
                .build();

        Item tea = ImmutableItem.builder()
                .description("Tasty tasty tea")
                .name("Box of Green Tea")
                .type(ItemType.JUST_RIGHT)
                .price(10D)
                .id(2L)
                .build();

        Item videoGameConsole = ImmutableItem.builder()
                .description("Latest and greatest")
                .name("Super great game machine!!!")
                .type(ItemType.LUXURY)
                .price(300D)
                .id(3L)
                .build();

        Item pagani_huayra = ImmutableItem.builder()
                .description("So fancy so fast")
                .name("pagani huayra")
                .type(ItemType.LUXURY)
                .price(1400000D)
                .id(4L)
                .build();

        map.put(
                soup.getId(),
                asList(soup, soup, soup)
        );
        map.put(
                tea.getId(),
                asList(tea, tea, tea, tea, tea)
        );
        map.put(
                videoGameConsole.getId(),
                asList(videoGameConsole, videoGameConsole)
        );
        map.put(pagani_huayra.getId(), Collections.singletonList(pagani_huayra));

        return map;
    }
}
