package solidPrinciples.I.InterfaceSegregation.shoppingCartExample;

import initialImplementation.Item;

import java.util.*;
import java.util.stream.Collectors;

public class FullyFeaturedWarehouse implements ReadWriteWarehouse {
    private final Map<Long, List<Item>> warehouse;

    public FullyFeaturedWarehouse() {
        this.warehouse = new HashMap<>();
    }

    @Override
    public Optional<Item> getItem(Long itemId) {
        List<Item> items = warehouse.getOrDefault(itemId, Collections.emptyList());

        Optional<Item> optionalItem = Optional.ofNullable(items)
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0));

        optionalItem.ifPresent(item -> {
            List<Item> updatedList = items.stream()
                    .limit(items.size() - 1)
                    .collect(Collectors.toList());

            warehouse.put(itemId, updatedList);
        });

        return optionalItem;
    }

    @Override
    public void addItem(Item item) {
        List<Item> existingItems = warehouse.getOrDefault(item.getId(), Collections.emptyList());

        List<Item> items = new ArrayList<>(existingItems);
        items.add(item);

        warehouse.put(item.getId(), items);
    }
}
