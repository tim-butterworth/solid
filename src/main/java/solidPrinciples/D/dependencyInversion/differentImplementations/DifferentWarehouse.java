package solidPrinciples.D.dependencyInversion.differentImplementations;

import initialImplementation.ImmutableItem;
import initialImplementation.Item;
import solidPrinciples.D.dependencyInversion.core.Warehouse;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.items.DatabaseDIItem;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.items.ItemRepo;

import java.util.Optional;

public class DifferentWarehouse implements Warehouse {

    private final ItemRepo repo;

    public DifferentWarehouse(ItemRepo repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Item> getItem(Long itemId) {
        repo.findByNAME("SOUP");
        return repo.findByITEM_ID(itemId).map(this::convertToItem);
    }

    private Item convertToItem(DatabaseDIItem item) {
        return ImmutableItem.builder()
                .description(item.getDescription())
                .name(item.getName())
                .id(item.getItemId())
                .price(item.getPrice())
                .type(item.getType())
                .build();
    }
}
