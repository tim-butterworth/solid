package solidPrinciples.D.dependencyInversion.core;

import initialImplementation.Item;

import java.util.Optional;

public interface Warehouse {
    Optional<Item> getItem(Long itemId);
}
