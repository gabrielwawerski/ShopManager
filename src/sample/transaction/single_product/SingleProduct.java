package sample.transaction.single_product;

import sample.product.Product;

import java.io.Serializable;

public class SingleProduct implements Serializable {
    private final String name;
    private final int quantity;
    private final double price;

    public SingleProduct(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static SingleProduct fromProduct(Product product) {
        return new SingleProduct(product.getName(), product.getQuantity(), product.getPrice());
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
