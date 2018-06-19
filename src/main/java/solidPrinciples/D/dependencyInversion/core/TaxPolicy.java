package solidPrinciples.D.dependencyInversion.core;

import initialImplementation.ItemType;

public interface TaxPolicy {
    Double getTaxRate(ItemType type);
}
