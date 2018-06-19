package solidPrinciples.D.dependencyInversion.differentImplementations;

import initialImplementation.ItemType;
import solidPrinciples.D.dependencyInversion.core.TaxPolicy;

public class DifferentTaxPolicy implements TaxPolicy {
    @Override
    public Double getTaxRate(ItemType type) {
        return null;
    }
}
