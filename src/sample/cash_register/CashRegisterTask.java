package sample.cash_register;

import javafx.beans.property.*;
import javafx.concurrent.Task;
import sample.app.Context;
import sample.transaction.TransactionBuilder;

// 4 kasy - kazda oddzielny watek?
public class CashRegisterTask extends Task<Void> {
    private IntegerProperty cashRegisterId;
    private StringProperty cashierName;
    private IntegerProperty transactionCount;
    private IntegerProperty currentTransactionId;
    private DoubleProperty subtotalCost;
    private DoubleProperty tax;
    private DoubleProperty totalCost;

    private static int id = 1;

    private final Context context;

    public CashRegisterTask(Context context) {
        this.context = context;
    }

    private void init() {
        initProperties();
        TransactionBuilder builder = new TransactionBuilder();

        cashRegisterId.set(id++);
        cashierName.set(CashRegisterHelper.randomCashierName());
        transactionCount.set(0);
        currentTransactionId.set(context.nextTransactionId());
    }

    private void initProperties() {
        cashRegisterId = new SimpleIntegerProperty();
        cashierName = new SimpleStringProperty();
        transactionCount = new SimpleIntegerProperty();
        currentTransactionId = new SimpleIntegerProperty();
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

    public int getCashRegisterId() {
        return cashRegisterId.get();
    }

    public IntegerProperty cashRegisterIdProperty() {
        return cashRegisterId;
    }

    public void setCashRegisterId(int cashRegisterId) {
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

    public int getTransactionCount() {
        return transactionCount.get();
    }

    public IntegerProperty transactionCountProperty() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount.set(transactionCount);
    }

    public int getCurrentTransactionId() {
        return currentTransactionId.get();
    }

    public IntegerProperty currentTransactionIdProperty() {
        return currentTransactionId;
    }

    public void setCurrentTransactionId(int currentTransactionId) {
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
