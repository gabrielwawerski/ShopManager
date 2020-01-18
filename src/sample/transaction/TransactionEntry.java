package sample.transaction;

import javafx.beans.property.*;

public class TransactionEntry {
    private StringProperty transactionDate;
    private IntegerProperty transactionId;
    private StringProperty cashier;
    private DoubleProperty transactionPrice;

    public TransactionEntry() {
    }

    public TransactionEntry(String transactionDate, int transactionId,
                            String cashier, double transactionPrice) {
        this.transactionDate = new SimpleStringProperty(transactionDate);
        this.transactionId = new SimpleIntegerProperty(transactionId);
        this.cashier = new SimpleStringProperty(cashier);
        this.transactionPrice = new SimpleDoubleProperty(transactionPrice);
    }

    public String getTransactionDate() {
        return transactionDate.get();
    }

    public StringProperty transactionDateProperty() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate.set(transactionDate);
    }

    public int getTransactionId() {
        return transactionId.get();
    }

    public IntegerProperty transactionIdProperty() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId.set(transactionId);
    }

    public String getCashier() {
        return cashier.get();
    }

    public StringProperty cashierProperty() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier.set(cashier);
    }

    public double getTransactionPrice() {
        return transactionPrice.get();
    }

    public DoubleProperty transactionPriceProperty() {
        return transactionPrice;
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPrice.set(transactionPrice);
    }
}
