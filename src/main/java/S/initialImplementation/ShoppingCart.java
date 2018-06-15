package S.initialImplementation;

import S.singleResponsibility.ImmutableItem;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart {

    private final List<Item> itemList;
    private final Map<Long, List<Item>> warehouse;

    public ShoppingCart() {
        this.itemList = new ArrayList<>();
        this.warehouse = new HashMap<>();

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

        warehouse.put(
                soup.getId(),
                Arrays.asList(soup, soup, soup)
        );
        warehouse.put(
                tea.getId(),
                Arrays.asList(tea, tea, tea, tea, tea)
        );
        warehouse.put(
                videoGameConsole.getId(),
                Arrays.asList(videoGameConsole, videoGameConsole)
        );
        warehouse.put(pagani_huayra.getId(), Collections.singletonList(pagani_huayra));
    }

    public CartAddResult addItem(Long itemId) {
        List<Item> items = warehouse.getOrDefault(itemId, Collections.emptyList());


        Item item = Optional.ofNullable(items)
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .orElse(null);

        Optional.ofNullable(item)
                .ifPresent(i -> {
                    int updatedSize = items.size() - 1;

                    List<Item> updatedList = items.stream()
                            .limit(updatedSize)
                            .collect(Collectors.toList());

                    warehouse.put(itemId, updatedList);
                    itemList.add(i);
                });

        return Optional.ofNullable(item)
                .map(i -> CartAddResult.SUCCESS)
                .orElse(CartAddResult.FAILURE);
    }

    public Double calculateBill() {
        Double total = 0D;

        for (Item item : itemList) {
            Double taxFactor = 1.0;

            ItemType itemType = item.getType();
            if (itemType == ItemType.ESSENTIAL) {
                taxFactor = 1.0;
            }
            if (itemType == ItemType.JUST_RIGHT) {
                taxFactor = 1.08;
            } else if (itemType == ItemType.LUXURY) {
                taxFactor = 1.15;
            }

            Double discountRate = 0.0;
            if (item.getId() == 3L) {
                discountRate = .15;
            }

            Double price = item.getPrice();
            total = total - (price * discountRate) + price * taxFactor;
        }

        return total;
    }
}
