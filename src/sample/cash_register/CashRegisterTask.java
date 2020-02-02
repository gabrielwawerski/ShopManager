package sample.cash_register;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import sample.app.Context;
import sample.transaction.TransactionBuilder;
import sample.util.Util;

// 4 kasy - kazda oddzielny watek?
public class CashRegisterTask extends Task<Void> {
    private ObservableList<CashRegisterProperty> transactionProductList;

    private static int id = 1;

    private final Context context;
    private TransactionBuilder builder;

    private StringProperty cashRegisterId;
    private StringProperty cashierName;
    private IntegerProperty transactionCount;
    private IntegerProperty currentTransactionId;
    private StringProperty subtotalCost;
    private StringProperty tax;
    private StringProperty totalCost;

    private double _totalCost;

    public CashRegisterTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void call() throws Exception {
        init();

        while (true) {
            int i;

            do {
                sleep(200, 1000);

                CashRegisterProperty property = builder.productScan();

                boolean found = false;

                for (CashRegisterProperty x : transactionProductList) {
                    if (x.getProductName().equals(property.getProductName())) {
                        System.out.println("CONTAINS!: " + property.getProductName());
                        x.setQuantity(x.getQuantity() + property.getQuantity());
                        found = true;
                    }
                }


                if (!found) {
                    transactionProductList.add(property);
                }


                Platform.runLater(() -> {
                    totalCost.set(builder.getTotalCost() + " zł");
                });

                sleep(200, 1500);

                i = Util.random(0, 20);
            } while (i <= 18);
            i = 0;
            builder.build();
            reset();
        }
//            return null;
    }

    private void reset() {
        Platform.runLater(() -> {
            totalCost.set("");
        });
        builder.reset();
        transactionProductList.clear();
    }

    private void sleep(int sleepTimeMin, int sleepTimeMax) {
        try {
            Thread.sleep(Util.random(sleepTimeMin, sleepTimeMax));
        } catch (InterruptedException interrupted) {
            if (isCancelled()) {
                updateMessage("Cancelled");
//                    break;
            }
        }
    }

    public ObservableList<CashRegisterProperty> getTransactionProductList() {
        return transactionProductList;
    }

    private void init() {
        initProperties();
        builder = new TransactionBuilder();
        transactionProductList = FXCollections.observableArrayList();
        _totalCost = 0;

        Platform.runLater(() -> {
            cashRegisterId.set("Register " + getId());
            cashierName.set(CashRegisterHelper.randomCashierName());
            transactionCount.set(0);
            currentTransactionId.set(context.getNextTransactionId());
        });
    }

    private void initProperties() {
        cashRegisterId = new SimpleStringProperty();
        cashierName = new SimpleStringProperty();
        transactionCount = new SimpleIntegerProperty();
        currentTransactionId = new SimpleIntegerProperty();
        subtotalCost = new SimpleStringProperty();
        tax = new SimpleStringProperty();
        totalCost = new SimpleStringProperty();
    }

    private int getId() {
        return id++;
    }

    public final String getCashRegisterId() {
        return cashRegisterId.get();
    }

    public StringProperty cashRegisterIdProperty() {
        return cashRegisterId;
    }

    public final void setCashRegisterId(String cashRegisterId) {
        this.cashRegisterId.set(cashRegisterId);
    }

    public final String getCashierName() {
        return cashierName.get();
    }

    public StringProperty cashierNameProperty() {
        return cashierName;
    }

    public final void setCashierName(String cashierName) {
        this.cashierName.set(cashierName);
    }

    public final int getTransactionCount() {
        return transactionCount.get();
    }

    public IntegerProperty transactionCountProperty() {
        return transactionCount;
    }

    public final void setTransactionCount(int transactionCount) {
        this.transactionCount.set(transactionCount);
    }

    public final int getCurrentTransactionId() {
        return currentTransactionId.get();
    }

    public IntegerProperty currentTransactionIdProperty() {
        return currentTransactionId;
    }

    public final void setCurrentTransactionId(int currentTransactionId) {
        this.currentTransactionId.set(currentTransactionId);
    }

    public final String getSubtotalCost() {
        return subtotalCost.get();
    }

    public StringProperty subtotalCostProperty() {
        return subtotalCost;
    }

    public final void setSubtotalCost(String subtotalCost) {
        this.subtotalCost.set(subtotalCost);
    }

    public final String getTax() {
        return tax.get();
    }

    public StringProperty taxProperty() {
        return tax;
    }

    public final void setTax(String tax) {
        this.tax.set(tax);
    }

    public final String getTotalCost() {
        return totalCost.get();
    }

    public StringProperty totalCostProperty() {
        return totalCost;
    }

    public final void setTotalCost(String totalCost) {
        this.totalCost.set(totalCost);
    }
}
