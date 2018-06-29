package solidPrinciples.O.openClosed.justRight;

import initialImplementation.Item;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OpenClosedWarehouse {
    private final Map<Long, List<Item>> warehouse;

    public OpenClosedWarehouse(Map<Long, List<Item>> itemList) {
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

        return optionalItem;
    }
}
