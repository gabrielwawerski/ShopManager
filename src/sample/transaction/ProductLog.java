package sample.transaction;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductLog implements Serializable {
    private final ArrayList<SingleProduct> products;

    public ProductLog() {
        products = new ArrayList<>();
    }

    /**
     * Adds {@link SingleProduct} to the {@link #products} ArrayList. If product is in the arraylist already,
     * updates it's quantity instead.
     */
    public void add(SingleProduct product) {
        if (contains(product)) {
            addQuantity(product);
        } else {
            products.add(product);
        }
    }

    public boolean contains(SingleProduct product) {
        for (SingleProduct x : products) {
            if (x.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }

    private void addQuantity(SingleProduct scannedProduct) {
        retrieve(scannedProduct).addQuantity(scannedProduct.getQuantity());
    }

    private SingleProduct retrieve(SingleProduct product) {
        for (SingleProduct x : products) {
            if (x.getName().equals(product.getName())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Error! fixme!");
    }

    public ArrayList<SingleProduct> get() {
        return products;
    }
}
