package solidPrinciples.D.dependencyInversion.differentImplementations;

import initialImplementation.Item;
import solidPrinciples.D.dependencyInversion.core.TaxPolicy;

public class BetterTaxPolicy implements TaxPolicy {
    @Override
    public Double getTaxRate(Item item) {
        return null;
    }
}
