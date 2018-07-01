package solidPrinciples.L.liskovSubstitution.shoppingCartExample;

import initialImplementation.ItemType;

import java.util.Map;

public class LiskovTaxPolicy {

    private Map<ItemType, Double> itemTypeToTaxRate;

    public LiskovTaxPolicy(Map<ItemType, Double> itemTypeToTaxRate) {
        this.itemTypeToTaxRate = itemTypeToTaxRate;
    }

    public Double getTaxRate(ItemType type) {
        return itemTypeToTaxRate.getOrDefault(type, 1.0);
    }
}