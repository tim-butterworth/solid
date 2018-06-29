package solidPrinciples.D.dependencyInversion.differentImplementations.database.items;

import initialImplementation.ItemType;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.converters.ItemTypeConverter;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseColumn;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseID;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseTable;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.entityAnnotations.DatabaseTypeConverter;

@DatabaseTable("WAREHOUSE")
public class DatabaseDIItem implements DIItem {

    @DatabaseID("ID")
    private Long id;

    @DatabaseColumn("ITEM_ID")
    private Long itemId;

    @DatabaseColumn("TYPE")
    private ItemType type;

    @DatabaseColumn("NAME")
    private String name;

    @DatabaseColumn("DESCRIPTION")
    private String description;

    @DatabaseColumn("PRICE")
    private Double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public ItemType getType() {
        return type;
    }

    @DatabaseTypeConverter(ItemTypeConverter.class)
    public void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
