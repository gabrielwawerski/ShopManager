package sample.cash_register;

import javafx.beans.property.*;
import javafx.concurrent.Task;
import sample.transaction.TransactionBuilder;

// 4 kasy - kazda oddzielny watek?
public class CashRegisterTask extends Task<Void> {
    private StringProperty cashRegisterId;
    private StringProperty cashierName;
    private StringProperty transactionCount;
    private StringProperty currentTransactionId;
    private DoubleProperty subtotalCost;
    private DoubleProperty tax;
    private DoubleProperty totalCost;


    public CashRegisterTask() {
    }

    private void init() {
        initProperties();
        TransactionBuilder builder = new TransactionBuilder();

        cashierName.set(CashRegisterHelper.randomCashierName());
        transactionCount.set("0");
    }

    private void initProperties() {
        cashRegisterId = new SimpleStringProperty();
        cashierName = new SimpleStringProperty();
        transactionCount = new SimpleStringProperty();
        currentTransactionId = new SimpleStringProperty();
        subtotalCost = new SimpleDoubleProperty();
        tax = new SimpleDoubleProperty();
        totalCost = new SimpleDoubleProperty();
    }

    @Override
    protected Void call() throws Exception {
        init();

        while (true) {


            //region proper thread.sleep()
            try {
                Thread.sleep(100);
            } catch (InterruptedException interrupted) {
                if (isCancelled()) {
                    updateMessage("Cancelled");
//                    break;
                }
            }
            //endregion
        }
//            return null;
    }

    public String getCashRegisterId() {
        return cashRegisterId.get();
    }

    public StringProperty cashRegisterIdProperty() {
        return cashRegisterId;
    }

    public void setCashRegisterId(String cashRegisterId) {
        this.cashRegisterId.set(cashRegisterId);
    }

    public String getCashierName() {
        return cashierName.get();
    }

    public StringProperty cashierNameProperty() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName.set(cashierName);
    }

    public String getTransactionCount() {
        return transactionCount.get();
    }

    public StringProperty transactionCountProperty() {
        return transactionCount;
    }

    public void setTransactionCount(String transactionCount) {
        this.transactionCount.set(transactionCount);
    }

    public String getCurrentTransactionId() {
        return currentTransactionId.get();
    }

    public StringProperty currentTransactionIdProperty() {
        return currentTransactionId;
    }

    public void setCurrentTransactionId(String currentTransactionId) {
        this.currentTransactionId.set(currentTransactionId);
    }

    public double getSubtotalCost() {
        return subtotalCost.get();
    }

    public DoubleProperty subtotalCostProperty() {
        return subtotalCost;
    }

    public void setSubtotalCost(double subtotalCost) {
        this.subtotalCost.set(subtotalCost);
    }

    public double getTax() {
        return tax.get();
    }

    public DoubleProperty taxProperty() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax.set(tax);
    }

    public double getTotalCost() {
        return totalCost.get();
    }

    public DoubleProperty totalCostProperty() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost.set(totalCost);
    }
}
