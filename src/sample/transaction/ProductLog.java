package sample.transaction;

import sample.transaction.single_product.SingleProduct;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductLog implements Serializable {
    private final ArrayList<SingleProduct> products;

    public ProductLog() {
        products = new ArrayList<>();
    }

    public void put(SingleProduct product) {
        products.add(product);
    }

    public ArrayList<SingleProduct> get() {
        return products;
    }
}
