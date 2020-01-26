package sample.cash_register;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CashRegisterProperty {
    private IntegerProperty id;
    private StringProperty product;
    private IntegerProperty quantity;
    private DoubleProperty price;

    public CashRegisterProperty(int id, String product, int quantity, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.product = new SimpleStringProperty(product);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }

    public final int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public final void setId(int id) {
        this.id.set(id);
    }

    public final String getProduct() {
        return product.get();
    }

    public StringProperty productProperty() {
        return product;
    }

    public final void setProduct(String product) {
        this.product.set(product);
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
