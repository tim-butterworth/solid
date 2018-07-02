import initialImplementation.*;
import solidPrinciples.D.dependencyInversion.containers.DifferentImplementationShoppingCartFactory;
import solidPrinciples.D.dependencyInversion.containers.InMemoryShoppingCartFactory;
import solidPrinciples.I.InterfaceSegregation.shoppingCartExample.FullyFeaturedWarehouse;
import solidPrinciples.I.InterfaceSegregation.shoppingCartExample.InterfaceSegregationShoppingCart;
import solidPrinciples.L.liskovSubstitution.shoppingCartExample.*;
import solidPrinciples.O.openClosed.justRight.*;
import solidPrinciples.S.singleResponsibility.SingleResponsibilityShoppingCart;

import java.util.*;

import static java.util.Arrays.asList;

public class Demonstrator {

    public static void main(String[] args) {
        ShoppingCart initialShoppingCart = new InitialShoppingCart();
        ShoppingCart singleResponsibilityShoppingCart = new SingleResponsibilityShoppingCart();
        ShoppingCart openClosedShoppingCart = getOpenClosedShoppingCart();
        ShoppingCart liskovSubstitutionShoppingCart = getLiskovShoppingCart();
        ShoppingCart interfaceSegregationShoppingCart = getInterfaceSegregationShoppingCart();

        List<ShoppingCart> shoppingCarts = Arrays.asList(
                initialShoppingCart,
                singleResponsibilityShoppingCart,
                openClosedShoppingCart,
                liskovSubstitutionShoppingCart,
                interfaceSegregationShoppingCart,
                new InMemoryShoppingCartFactory().getInstance(),
                new DifferentImplementationShoppingCartFactory().getInstance()
        );

        shoppingCarts.forEach(shoppingCart -> {
            try {
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
            } catch (ItemNotFound e) {
                System.out.println();
                System.out.println(shoppingCart.getClass().getSimpleName() + " had the following error: " + e.getMessage());
                System.out.println();
            }
        });
    }

    private static LiskovShoppingCart getLiskovShoppingCart() {
        return new LiskovShoppingCart(
                new LiskovPricing(
                        new LiskovDiscountPolicy(getDiscountMap()),
                        new LiskovTaxPolicy(getTaxMap())
                ),
                new LiskovWarehouse(getWarehouseItems())
        );
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
        OpenClosedPricing pricing = new OpenClosedPricing(
                discountPolicy,
                taxPolicy
        );

        return new OpenClosedShoppingCart(pricing, warehouse);
    }

    private static OpenClosedTaxPolicy getOpenClosedTaxPolicy() {
        return new OpenClosedTaxPolicy(getTaxMap());
    }

    private static OpenClosedDiscountPolicy getOpenClosedDiscountPolicy() {
        return new OpenClosedDiscountPolicy(getDiscountMap());
    }

    private static Map<ItemType, Double> getTaxMap() {
        Map<ItemType, Double> itemTypeToTaxRate = new HashMap<>();
        itemTypeToTaxRate.put(ItemType.ESSENTIAL, 1.0);
        itemTypeToTaxRate.put(ItemType.JUST_RIGHT, 1.08);
        itemTypeToTaxRate.put(ItemType.LUXURY, 1.15);
        return itemTypeToTaxRate;
    }

    private static Map<Long, Double> getDiscountMap() {
        Map<Long, Double> itemIdToDiscountMap = new HashMap<>();
        itemIdToDiscountMap.put(3L, .85);
        return itemIdToDiscountMap;
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
