package solidPrinciples.D.dependencyInversion.core;

import initialImplementation.Item;

public interface Pricing {
    double getItemPrice(Item item);
}
