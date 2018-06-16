package solidPrinciples.S.singleResponsibility;

import initialImplementation.Item;
import initialImplementation.ItemType;

public class TaxPolicy {
    public Double getTaxRate(Item item) {
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
        return taxFactor;
    }
}