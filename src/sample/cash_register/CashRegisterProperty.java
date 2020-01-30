package sample.cash_register;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CashRegisterProperty {
    private IntegerProperty productRow;
    private StringProperty productName;
    private IntegerProperty quantity;
    private DoubleProperty price;

    public CashRegisterProperty(int productRow, String productName, int quantity, double price) {
        this.productRow = new SimpleIntegerProperty(productRow);
        this.productName = new SimpleStringProperty(productName);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }

    public final int getProductRow() {
        return productRow.get();
    }

    public IntegerProperty productRowProperty() {
        return productRow;
    }

    public final void setProductRow(int productRow) {
        this.productRow.set(productRow);
    }

    public final String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public final void setProductName(String productName) {
        this.productName.set(productName);
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
