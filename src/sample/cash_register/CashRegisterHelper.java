package sample.cash_register;

import sample.app.database.DatabaseHandler;
import sample.app.product.Product;
import sample.util.Util;

import java.text.DecimalFormat;
import java.util.ArrayList;

public final class CashRegisterHelper {
    private static ArrayList<Product> databaseProducts = DatabaseHandler.getInstance().getProductArrayList();
    private static final DecimalFormat FORMATTER = new DecimalFormat("#.##");

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

    private static ArrayList<String> usedNames = new ArrayList<>(4);

    public static String randomCashierName() {
        String random = NAMES[Util.random(0, NAMES.length - 1)];

        if (!usedNames.contains(random)) {
            usedNames.add(random);
        } else {
            while (usedNames.contains(random)) {
                random = NAMES[Util.random(0, NAMES.length - 1)];
            }
        }
        return random;
    }

    public static void updateProductPrice(CashRegisterProperty property) {
        property.setPrice(FORMATTER.format(findProduct(property).getPrice() * property.getQuantity()));
    }

    private static Product findProduct(CashRegisterProperty property) {
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

    // TODO add pseudorandom quantity
    // e.g. more probable for client to buy more than 1 bulki, than to buy more than 1 mleko
    public static int randomQuantity() {
        int quantity;
        int random = Util.random(0, 4);

        if (random >= 1) {
            quantity = 1;
        } else {
            random = Util.random(0, 10);

            if (random < 8) {
                quantity = Util.random(1, 3);
            } else {
                quantity = Util.random(1, 10);
            }
        }
        return quantity;
    }
}
