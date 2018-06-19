package solidPrinciples.O.openClosed.justRight;

import initialImplementation.ItemType;

import java.util.Map;

public class OpenClosedTaxPolicy {

    private Map<ItemType, Double> itemTypeToTaxRate;

    public OpenClosedTaxPolicy(Map<ItemType, Double> itemTypeToTaxRate) {
        this.itemTypeToTaxRate = itemTypeToTaxRate;
    }

    public Double getTaxRate(ItemType type) {
        return itemTypeToTaxRate.getOrDefault(type, 1.0);
    }
}