package initialImplementation;

public interface ShoppingCart {
    CartAddResult addItem(Long itemId);

    Double calculateBill();
}
