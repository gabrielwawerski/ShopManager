package sample.transaction;

import sample.product.Product;

public class ProductPrint {
    private final String name;
    private final int quantity;
    private final double price;

    public ProductPrint(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static ProductPrint fromProduct(Product product) {
        return new ProductPrint(product.getName(), product.getQuantity(), product.getPrice());
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
