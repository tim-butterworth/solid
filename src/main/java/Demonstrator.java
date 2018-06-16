import initialImplementation.InitialShoppingCart;
import initialImplementation.ShoppingCart;
import solidPrinciples.S.singleResponsibility.SingleResponsibilityShoppingCart;

import java.util.Arrays;
import java.util.List;

public class Demonstrator {

    public static void main(String[] args) {
        ShoppingCart initialShoppingCart = new InitialShoppingCart();
        ShoppingCart singleResponsibilityShoppingCart = new SingleResponsibilityShoppingCart();

        List<ShoppingCart> shoppingCarts = Arrays.asList(
                initialShoppingCart,
                singleResponsibilityShoppingCart
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
}
