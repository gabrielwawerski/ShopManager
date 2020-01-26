package sample.product;

import javafx.beans.property.*;

public class ProductProperty {
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty quantity;
    private DoubleProperty price;

    public ProductProperty() {
    }

    public ProductProperty(int id, String name, int quantity, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public final int getId() {
        return id.get();
    }

    public final void setId(int id) {
        this.id.set(id);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public final String getName() {
        return name.get();
    }

    public final void setName(String name) {
        this.name.set(name);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public final int getQuantity() {
        return quantity.get();
    }

    public final void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public final double getPrice() {
        return price.get();
    }

    public final void setPrice(double price) {
        this.price.set(price);
    }
}
