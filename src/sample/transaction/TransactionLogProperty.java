package sample.transaction;

import javafx.beans.property.*;

public class TransactionLogProperty {
    private StringProperty transactionDate;
    private IntegerProperty transactionId;
    private StringProperty cashier;
    private DoubleProperty transactionCost;

    public TransactionLogProperty() {
    }

    public TransactionLogProperty(String transactionDate, int transactionId,
                                  String cashier, double transactionCost) {
        this.transactionDate = new SimpleStringProperty(transactionDate);
        this.transactionId = new SimpleIntegerProperty(transactionId);
        this.cashier = new SimpleStringProperty(cashier);
        this.transactionCost = new SimpleDoubleProperty(transactionCost);
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

    public final double getTransactionCost() {
        return transactionCost.get();
    }

    public DoubleProperty transactionCostProperty() {
        return transactionCost;
    }

    public final void setTransactionCost(double transactionCost) {
        this.transactionCost.set(transactionCost);
    }
}
