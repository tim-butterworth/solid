package solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.converters;

import initialImplementation.ItemType;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.Converter;

import java.util.Arrays;

public class ItemTypeConverter implements Converter {
    @Override
    public Object apply(Object object) {
        return Arrays.stream(ItemType.values())
                .filter(itemType -> itemType.name().equals(object))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("%s did not match any item type", object)));
    }
}
