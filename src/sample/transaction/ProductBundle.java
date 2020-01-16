package sample.transaction;

import sample.product.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductBundle implements Serializable {
    private ArrayList<ProductPrint> products;

    public ProductBundle() {
        products = new ArrayList<>();
    }

    public void put(Product product) {
        products.add(ProductPrint.fromProduct(product));
    }

    public ArrayList<ProductPrint> getProducts() {
        return products;
    }
}
