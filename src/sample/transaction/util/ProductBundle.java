package sample.transaction.util;

import sample.product.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductBundle implements Serializable {
    private ArrayList<ProductLog> products;

    public ProductBundle() {
        products = new ArrayList<>();
    }

    public void put(Product product) {
        products.add(ProductLog.fromProduct(product));
    }

    public ArrayList<ProductLog> getProducts() {
        return products;
    }
}
