package solidPrinciples.D.dependencyInversion.containers;

import initialImplementation.ItemType;
import initialImplementation.ShoppingCart;
import solidPrinciples.D.dependencyInversion.core.DependencyInversionShoppingCart;
import solidPrinciples.D.dependencyInversion.differentImplementations.DifferentDiscountPolicy;
import solidPrinciples.D.dependencyInversion.differentImplementations.DifferentPricing;
import solidPrinciples.D.dependencyInversion.differentImplementations.DifferentTaxPolicy;
import solidPrinciples.D.dependencyInversion.differentImplementations.DifferentWarehouse;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.discounts.DatabaseDiscount;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.discounts.DiscountRepo;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.h2Tooling.ConnectionWrapper;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.items.DatabaseDIItem;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.items.ImmutableDIItem;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.items.ItemRepo;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.Repo;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.repoTooling.RepoBuilder;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.taxes.DatabaseTax;
import solidPrinciples.D.dependencyInversion.differentImplementations.database.taxes.TaxRepo;

public class DifferentImplementationShoppingCartFactory {
    public ShoppingCart getInstance() {
        RepoBuilder repoBuilder = new RepoBuilder(new ConnectionWrapper());

        ItemRepo itemRepo = repoBuilder.build(ItemRepo.class);
        DiscountRepo discountRepo = repoBuilder.build(DiscountRepo.class);
        TaxRepo taxRepo = repoBuilder.build(TaxRepo.class);

        populateItems(itemRepo);
        populateTaxes(taxRepo);
        populateDiscounts(discountRepo);

        return new DependencyInversionShoppingCart(
                new DifferentWarehouse(itemRepo),
                new DifferentPricing(
                        new DifferentTaxPolicy(taxRepo),
                        new DifferentDiscountPolicy(discountRepo)
                )
        );
    }

    private void populateItems(Repo<DatabaseDIItem, Long> itemRepo) {
        ImmutableDIItem soup = ImmutableDIItem.builder()
                .description("best description")
                .itemId(1L)
                .name("Soup")
                .price(.99D)
                .type(ItemType.ESSENTIAL)
                .build();

        ImmutableDIItem tea = ImmutableDIItem.builder()
                .description("Tasty tasty tea")
                .itemId(2L)
                .name("Box of Green Tea")
                .price(10D)
                .type(ItemType.JUST_RIGHT)
                .build();

        ImmutableDIItem videoGameConsole = ImmutableDIItem.builder()
                .description("Latest and greatest")
                .name("Super great game machine!!!")
                .type(ItemType.LUXURY)
                .price(300D)
                .itemId(3L)
                .build();

        ImmutableDIItem pagani_huayra = ImmutableDIItem.builder()
                .description("So fancy so fast")
                .name("pagani huayra")
                .type(ItemType.LUXURY)
                .price(1400000D)
                .itemId(4L)
                .build();

        itemRepo.save(getItem(soup));
        itemRepo.save(getItem(soup));
        itemRepo.save(getItem(soup));

        itemRepo.save(getItem(tea));
        itemRepo.save(getItem(tea));
        itemRepo.save(getItem(tea));
        itemRepo.save(getItem(tea));
        itemRepo.save(getItem(tea));

        itemRepo.save(getItem(videoGameConsole));
        itemRepo.save(getItem(videoGameConsole));

        itemRepo.save(getItem(pagani_huayra));
    }

    private DatabaseDIItem getItem(ImmutableDIItem immutableItem) {
        DatabaseDIItem item = new DatabaseDIItem();
        item.setDescription(immutableItem.getDescription());
        item.setName(immutableItem.getName());
        item.setPrice(immutableItem.getPrice());
        item.setType(immutableItem.getType());
        item.setItemId(immutableItem.getItemId());

        return item;
    }

    private void populateDiscounts(Repo<DatabaseDiscount, Long> discountRepo) {
        DatabaseDiscount databaseDiscount = new DatabaseDiscount();
        databaseDiscount.setDiscountRate(.85);
        databaseDiscount.setItemId(3L);

        discountRepo.save(databaseDiscount);
    }

    private void populateTaxes(Repo<DatabaseTax, Long> taxRepo) {
        taxRepo.save(getTaxEntity(ItemType.ESSENTIAL, 1.00));
        taxRepo.save(getTaxEntity(ItemType.JUST_RIGHT, 1.08));
        taxRepo.save(getTaxEntity(ItemType.LUXURY, 1.15));
    }

    private DatabaseTax getTaxEntity(ItemType essential, double taxRate) {
        DatabaseTax databaseTax = new DatabaseTax();
        databaseTax.setItemType(essential);
        databaseTax.setTaxRate(taxRate);

        return databaseTax;
    }

}
