package S;

import S.initialImplementation.ShoppingCart;

public class SingleResponsibilityDemonstrator {

    public static void main(String[] args) {
        ShoppingCart shoppingCartMixed = new ShoppingCart();

        System.out.println(shoppingCartMixed.addItem(1L));
        System.out.println(shoppingCartMixed.addItem(1L));
        System.out.println(shoppingCartMixed.addItem(1L));
        System.out.println(shoppingCartMixed.addItem(1L));
        System.out.println(shoppingCartMixed.addItem(1L));
        System.out.println(shoppingCartMixed.addItem(2L));
        System.out.println(shoppingCartMixed.addItem(2L));
        System.out.println(shoppingCartMixed.addItem(2L));
        System.out.println(shoppingCartMixed.addItem(4L));
        System.out.println(shoppingCartMixed.addItem(9L));

        System.out.println(shoppingCartMixed.calculateBill());
    }
}
