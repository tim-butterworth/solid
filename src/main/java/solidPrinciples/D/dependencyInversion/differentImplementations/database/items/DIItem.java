package solidPrinciples.D.dependencyInversion.differentImplementations.database.items;

import initialImplementation.ItemType;
import org.immutables.value.Value;

@Value.Immutable
public interface DIItem {
    Long getItemId();
    String getName();
    String getDescription();
    Double getPrice();
    ItemType getType();
}
