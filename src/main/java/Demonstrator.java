import initialImplementation.InitialShoppingCart;
import initialImplementation.ItemType;
import initialImplementation.ShoppingCart;
import solidPrinciples.D.dependencyInversion.containers.DifferentImplementationShoppingCartFactory;
import solidPrinciples.D.dependencyInversion.containers.InMemoryShoppingCartFactory;
import solidPrinciples.O.openClosed.justRight.OpenClosedDiscountPolicy;
import solidPrinciples.O.openClosed.justRight.OpenClosedShoppingCart;
import solidPrinciples.O.openClosed.justRight.OpenClosedTaxPolicy;
import solidPrinciples.S.singleResponsibility.SingleResponsibilityShoppingCart;

import java.util.*;

public class Demonstrator {

    public static void main(String[] args) {
        ShoppingCart initialShoppingCart = new InitialShoppingCart();
        ShoppingCart singleResponsibilityShoppingCart = new SingleResponsibilityShoppingCart();
        ShoppingCart openClosedShoppingCart = getOpenClosedShoppingCart();

        List<ShoppingCart> shoppingCarts = Arrays.asList(
                initialShoppingCart,
                singleResponsibilityShoppingCart,
                openClosedShoppingCart,
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

            System.out.println(shoppingCart.calculateBill());
        });
    }

    private static ShoppingCart getOpenClosedShoppingCart() {
        Map<Long, Double> itemIdToDiscountMap = new HashMap<>();
        itemIdToDiscountMap.put(3L, .85);

        Map<ItemType, Double> itemTypeToTaxRate = new HashMap<>();
        itemTypeToTaxRate.put(ItemType.ESSENTIAL, 1.0);
        itemTypeToTaxRate.put(ItemType.JUST_RIGHT, 1.08);
        itemTypeToTaxRate.put(ItemType.LUXURY, 1.15);

        return new OpenClosedShoppingCart(
                new OpenClosedDiscountPolicy(
                        itemIdToDiscountMap
                ),
                new OpenClosedTaxPolicy(
                        itemTypeToTaxRate
                )
        );
    }
}
