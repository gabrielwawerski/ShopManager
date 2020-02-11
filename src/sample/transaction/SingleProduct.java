package sample.transaction;

import sample.app.product.Product;

import java.io.Serializable;

public class SingleProduct implements Serializable {
    private final String name;
    private int quantity;
    private double price;

    public SingleProduct(Product product, int quantity) {
        this.name = product.getName();
        this.quantity = quantity;
        this.price = product.getPrice();
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
        this.price *= this.quantity;
    }

    public double getPrice() {
        return price;
    }
}
