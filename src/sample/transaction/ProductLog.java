package sample.transaction;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductLog implements Serializable {
    private final ArrayList<Product> products;

    public ProductLog() {
        products = new ArrayList<>();
    }

    /**
     * Adds {@link Product} to the {@link #products} ArrayList. If product is in the arraylist already,
     * updates it's quantity instead.
     */
    public void add(Product product) {
        if (contains(product)) {
            addQuantity(product);
        } else {
            products.add(product);
        }
    }

    public boolean contains(Product product) {
        for (Product x : products) {
            if (x.getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        products.clear();
    }

    private void addQuantity(Product scannedProduct) {
        retrieve(scannedProduct).addQuantity(scannedProduct.getQuantity());
    }

    private Product retrieve(Product product) {
        for (Product x : products) {
            if (x.getId().equals(product.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Error! fixme!");
    }

    public ArrayList<Product> get() {
        return products;
    }
}
