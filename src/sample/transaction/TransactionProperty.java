package sample.transaction;

import javafx.beans.property.*;

public class TransactionProperty {
    private StringProperty transactionDate;
    private IntegerProperty transactionId;
    private StringProperty cashier;
    private DoubleProperty transactionPrice;

    public TransactionProperty() {
    }

    public TransactionProperty(String transactionDate, int transactionId,
                               String cashier, double transactionPrice) {
        this.transactionDate = new SimpleStringProperty(transactionDate);
        this.transactionId = new SimpleIntegerProperty(transactionId);
        this.cashier = new SimpleStringProperty(cashier);
        this.transactionPrice = new SimpleDoubleProperty(transactionPrice);
    }

    public final String getTransactionDate() {
        return transactionDate.get();
    }

    public StringProperty transactionDateProperty() {
        return transactionDate;
    }

    public final void setTransactionDate(String transactionDate) {
        this.transactionDate.set(transactionDate);
    }

    public final int getTransactionId() {
        return transactionId.get();
    }

    public IntegerProperty transactionIdProperty() {
        return transactionId;
    }

    public final void setTransactionId(int transactionId) {
        this.transactionId.set(transactionId);
    }

    public String getCashier() {
        return cashier.get();
    }

    public StringProperty cashierProperty() {
        return cashier;
    }

    public final void setCashier(String cashier) {
        this.cashier.set(cashier);
    }

    public final double getTransactionPrice() {
        return transactionPrice.get();
    }

    public DoubleProperty transactionPriceProperty() {
        return transactionPrice;
    }

    public final void setTransactionPrice(double transactionPrice) {
        this.transactionPrice.set(transactionPrice);
    }
}
