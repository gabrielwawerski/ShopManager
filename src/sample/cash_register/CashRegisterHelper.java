package sample.cash_register;

import sample.app.database.DatabaseHandler;
import sample.app.product.Product;
import sample.util.Util;

import java.util.ArrayList;

public final class CashRegisterHelper {
    private static ArrayList<Product> databaseProducts = DatabaseHandler.getInstance().getProductArrayList();

    private static final String[] NAMES = {
            "Aubrey Lewis", "Audrey Clarke", "Avery Wright",
            "Beverly Hudson", "Carter Wyatt", "Christian Hill",
            "Eleanor Grayson", "Ella Johnson", "Ellie Jaxon",
            "Evelyn Evans", "Jack Hughes", "Jackson Robinson",
            "James Smith", "Julian Harris", "Lillian Addison",
            "Lily Green", "Lincoln Hazel", "Lucy Jackson",
            "Mason Montgomery", "Scarlett Madison", "Taylor Harper",
            "William Jones"
    };

    public static String randomCashierName() {
        return NAMES[Util.random(0, NAMES.length - 1)];
    }

    public static Product findProduct(CashRegisterProperty property) {
        for (Product x : databaseProducts) {
            if (x.getName().equals(property.getProductName())) {
                return x;
            }
        }
        throw new IllegalArgumentException(
                "Invalid argument! product not found from property: " + property.getProductName());
    }

    public static Product randomProduct() {
        return databaseProducts.get(Util.random(0, databaseProducts.size() - 1));
    }
}
