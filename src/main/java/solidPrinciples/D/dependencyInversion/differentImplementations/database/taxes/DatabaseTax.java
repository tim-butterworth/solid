package solidPrinciples.D.dependencyInversion.differentImplementations.database.taxes;

import initialImplementation.ItemType;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.converters.ItemTypeConverter;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseColumn;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseID;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseTable;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseTypeConverter;

@DatabaseTable("TAX_TABLE")
public class DatabaseTax {

    @DatabaseID("ID")
    private Long id;

    @DatabaseColumn("ITEM_TYPE")
    private ItemType itemType;

    @DatabaseColumn("RATE")
    private Double taxRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    @DatabaseTypeConverter(ItemTypeConverter.class)
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
}
