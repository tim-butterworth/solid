package solidPrinciples.O.openClosed.justRight;

import initialImplementation.Item;
import initialImplementation.ItemType;

import java.util.Map;

public class OpenClosedTaxPolicy {

    private Map<ItemType, Double> itemTypeToTaxRate;

    public OpenClosedTaxPolicy(Map<ItemType, Double> itemTypeToTaxRate) {
        this.itemTypeToTaxRate = itemTypeToTaxRate;
    }

    public Double getTaxRate(Item item) {
        return itemTypeToTaxRate.getOrDefault(item.getType() , 1.0);
    }
}