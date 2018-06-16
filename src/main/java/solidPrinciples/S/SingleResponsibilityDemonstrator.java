package solidPrinciples.S;

import solidPrinciples.S.singleResponsibility.SingleResponsibilityShoppingCart;

public class SingleResponsibilityDemonstrator {

    public static void main(String[] args) {
        SingleResponsibilityShoppingCart singleResponsibilityShoppingCartMixed = new SingleResponsibilityShoppingCart();

        System.out.println(singleResponsibilityShoppingCartMixed.addItem(1L));
        System.out.println(singleResponsibilityShoppingCartMixed.addItem(1L));
        System.out.println(singleResponsibilityShoppingCartMixed.addItem(1L));
        System.out.println(singleResponsibilityShoppingCartMixed.addItem(1L));
        System.out.println(singleResponsibilityShoppingCartMixed.addItem(1L));
        System.out.println(singleResponsibilityShoppingCartMixed.addItem(2L));
        System.out.println(singleResponsibilityShoppingCartMixed.addItem(2L));
        System.out.println(singleResponsibilityShoppingCartMixed.addItem(2L));
        System.out.println(singleResponsibilityShoppingCartMixed.addItem(4L));
        System.out.println(singleResponsibilityShoppingCartMixed.addItem(9L));

        System.out.println(singleResponsibilityShoppingCartMixed.calculateBill());
    }
}
