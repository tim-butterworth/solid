package initialImplementation;

import org.immutables.value.Value;

@Value.Immutable
public interface Item {
    ItemType getType();
    String getName();
    String getDescription();
    Double getPrice();
    Long getId();
}
