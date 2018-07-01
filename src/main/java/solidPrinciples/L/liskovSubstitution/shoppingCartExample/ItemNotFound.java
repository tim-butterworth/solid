package solidPrinciples.L.liskovSubstitution.shoppingCartExample;

public class ItemNotFound extends RuntimeException {
    public ItemNotFound(String message) {
        super(message);
    }
}
