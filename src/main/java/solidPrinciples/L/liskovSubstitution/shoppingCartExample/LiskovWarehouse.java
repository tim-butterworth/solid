package solidPrinciples.L.liskovSubstitution.shoppingCartExample;

import initialImplementation.Item;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LiskovWarehouse {
    private final Map<Long, List<Item>> warehouse;

    public LiskovWarehouse(Map<Long, List<Item>> itemList) {
        this.warehouse = itemList;
    }

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

        if (optionalItem.isPresent()) return optionalItem;
        else throw new ItemNotFound("No item found!"); // could also return null
    }
}
