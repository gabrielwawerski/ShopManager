package sample.transaction.util;

import sample.product.Product;

public class ProductLog {
    private final String name;
    private final int quantity;
    private final double price;

    public ProductLog(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static ProductLog fromProduct(Product product) {
        return new ProductLog(product.getName(), product.getQuantity(), product.getPrice());
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
