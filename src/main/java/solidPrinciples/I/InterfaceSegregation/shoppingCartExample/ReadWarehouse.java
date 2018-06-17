package solidPrinciples.I.InterfaceSegregation.shoppingCartExample;

import initialImplementation.Item;

import java.util.Optional;

public interface ReadWarehouse {
    Optional<Item> getItem(Long itemId);
}
