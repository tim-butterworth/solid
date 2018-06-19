package solidPrinciples.D.dependencyInversion.differentImplementations;

import initialImplementation.Item;
import solidPrinciples.D.dependencyInversion.core.Warehouse;

import java.util.Optional;

public class DifferentWarehouse implements Warehouse {
    @Override
    public Optional<Item> getItem(Long itemId) {
        return Optional.empty();
    }
}
