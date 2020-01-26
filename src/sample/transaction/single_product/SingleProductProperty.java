package sample.transaction.single_product;

import javafx.beans.property.*;

public class SingleProductProperty {
    private StringProperty name;
    private IntegerProperty quantity;
    private DoubleProperty price;

    public SingleProductProperty(String name, int quantity, double price) {
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }

    public final String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public final void setName(String name) {
        this.name.set(name);
    }

    public final int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public final void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public final double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public final void setPrice(double price) {
        this.price.set(price);
    }
}
