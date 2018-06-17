package solidPrinciples.I.InterfaceSegregation.shoppingCartExample;

import initialImplementation.ImmutableItem;
import initialImplementation.Item;
import initialImplementation.ItemType;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class FullyFeaturedWarehouse implements ReadWriteWarehouse {
    private final Map<Long, List<Item>> warehouse;

    public FullyFeaturedWarehouse() {
        this.warehouse = buildWarehouse();
    }

    private Map<Long, List<Item>> buildWarehouse() {
        Map<Long, List<Item>> map = new HashMap<>();

        Item soup = ImmutableItem.builder()
                .description("Some fine soup")
                .name("Can of Soup")
                .type(ItemType.ESSENTIAL)
                .price(.99)
                .id(1L)
                .build();

        Item tea = ImmutableItem.builder()
                .description("Tasty tasty tea")
                .name("Box of Green Tea")
                .type(ItemType.JUST_RIGHT)
                .price(10D)
                .id(2L)
                .build();

        Item videoGameConsole = ImmutableItem.builder()
                .description("Latest and greatest")
                .name("Super great game machine!!!")
                .type(ItemType.LUXURY)
                .price(300D)
                .id(3L)
                .build();

        Item pagani_huayra = ImmutableItem.builder()
                .description("So fancy so fast")
                .name("pagani huayra")
                .type(ItemType.LUXURY)
                .price(1400000D)
                .id(4L)
                .build();

        map.put(
                soup.getId(),
                asList(soup, soup, soup)
        );
        map.put(
                tea.getId(),
                asList(tea, tea, tea, tea, tea)
        );
        map.put(
                videoGameConsole.getId(),
                asList(videoGameConsole, videoGameConsole)
        );
        map.put(pagani_huayra.getId(), Collections.singletonList(pagani_huayra));

        return map;
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
