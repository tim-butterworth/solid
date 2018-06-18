package solidPrinciples.D.dependencyInversion.core;

import initialImplementation.Item;

public interface TaxPolicy {
    Double getTaxRate(Item item);
}
