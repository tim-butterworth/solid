package solidPrinciples.D.dependencyInversion.differentImplementations.database.discounts;

import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseColumn;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseID;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseTable;

@DatabaseTable("DISCOUNT")
public class DatabaseDiscount {

    @DatabaseID("ID")
    private Long id;

    @DatabaseColumn("ITEM_ID")
    private Long itemId;

    @DatabaseColumn("DISCOUNT_RATE")
    private Double discountRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }
}
