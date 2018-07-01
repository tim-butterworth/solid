package solidPrinciples.D.dependencyInversion.inMemory;

import initialImplementation.ItemType;

public class InMemoryTaxPolicy {
    public Double getTaxRate(ItemType itemType) {
        Double taxFactor = 1.0;

        if (itemType == ItemType.ESSENTIAL) {
            taxFactor = 1.0;
        }
        if (itemType == ItemType.JUST_RIGHT) {
            taxFactor = 1.08;
        } else if (itemType == ItemType.LUXURY) {
            taxFactor = 1.15;
        }
        return taxFactor;
    }
}