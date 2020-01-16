package sample.transaction;

import javafx.beans.property.*;

public class TransactionEntry {
    private IntegerProperty productId;
    private StringProperty productName;
    private IntegerProperty productQuantity;
    private DoubleProperty productPrice;

    public TransactionEntry() {
    }

    public TransactionEntry(int id, String productName, int productQuantity, double productPrice) {
        this.productId = new SimpleIntegerProperty(id);
        this.productName = new SimpleStringProperty(productName);
        this.productQuantity = new SimpleIntegerProperty(productQuantity);
        this.productPrice = new SimpleDoubleProperty(productPrice);
    }

    public int getProductId() {
        return productId.get();
    }

    public IntegerProperty productIdProperty() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public int getProductQuantity() {
        return productQuantity.get();
    }

    public IntegerProperty productQuantityProperty() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity.set(productQuantity);
    }

    public double getProductPrice() {
        return productPrice.get();
    }

    public DoubleProperty productPriceProperty() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice.set(productPrice);
    }
}
