package sample.cash_register;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import sample.app.Context;
import sample.transaction.Transaction;
import sample.transaction.TransactionBuilder;
import sample.util.Util;

import java.text.DecimalFormat;

// 4 kasy - kazda oddzielny watek?
public class CashRegisterTask extends Task<Void> {
    private ObservableList<CashRegisterProperty> transactionProductList;

    private int id;
    private static int _id = 1;

    private final Context context;
    private TransactionBuilder builder;
    private DecimalFormat formatter = new DecimalFormat("#.##");

    private StringProperty cashRegisterId;
    private StringProperty cashierName;
    private IntegerProperty transactionCount;
    private IntegerProperty currentTransactionId;
    private StringProperty totalCost;

    public CashRegisterTask(Context context) {
        this.context = context;
        setId();
    }

    // TODO fix price formatting - allow only 2 digit precision
    @Override
    protected Void call() throws Exception {
        init();

        while (true) {
            if (isCancelled()) {
                System.out.println("cancelled");
                break;
            }
            System.out.println("transaction: " + transactionCount.get());

            CashRegisterProperty scannedProduct = builder.productScan();
            boolean wasAlreadyScanned = false;

            for (CashRegisterProperty x : transactionProductList) {
                if (x.getProductName().equals(scannedProduct.getProductName())) {
                    x.setQuantity(x.getQuantity() + scannedProduct.getQuantity());
                    x.setPrice(CashRegisterHelper.findProduct(x).getPrice() * x.getQuantity());
                    wasAlreadyScanned = true;
                    System.out.println("product scanned already!");
                }
            }
            if (!wasAlreadyScanned) {
                System.out.println("not yet scanned! adding product: " + scannedProduct.getProductName());
                transactionProductList.add(scannedProduct);
            }

            Platform.runLater(() -> {
                updateTotalCost(scannedProduct.getQuantity() * scannedProduct.getPrice());
            });

            if (Util.random(0, 20) > 15) {
                Transaction transaction = builder.build();
                context.submitTransaction(transaction);
                reset();
                prepareRegister();
                System.out.println("STATE RESET!");
            }
            sleep(200, 1500);
        }
        return null;
    }

    private void addTotalCost() {

    }

    private void updateTotalCost(double value) {
        setTotalCost(getTotalCost() + formatter.format(value) + " zł");
    }

    // TODO fix - sometimes resets twice
    private void prepareRegister() {
        Platform.runLater(() -> {
            setTransactionCount(getTransactionCount() + 1);
            setCurrentTransactionId(context.getNextTransactionId());
        });
        System.out.println("REGISTER " + getId() + " PREPARED!");
    }

    private void reset() {
        Platform.runLater(() -> {
            setTotalCost("");
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
                System.out.println("sleep stopping!");
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

        Platform.runLater(() -> {
            setCashRegisterId("Register " + getId());
            setCashierName(CashRegisterHelper.randomCashierName());
            setTransactionCount(0);
        });
    }

    private void initProperties() {
        cashRegisterId = new SimpleStringProperty();
        cashierName = new SimpleStringProperty();
        transactionCount = new SimpleIntegerProperty();
        currentTransactionId = new SimpleIntegerProperty();
        totalCost = new SimpleStringProperty();
    }

    public int getId() {
        return id;
    }

    private void setId() {
        id = _id++;
    }

    //region Properties
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

    public final String getTotalCost() {
        return totalCost.get();
    }

    public StringProperty totalCostProperty() {
        return totalCost;
    }

    public final void setTotalCost(String totalCost) {
        this.totalCost.set(totalCost);
    }
    //endregion
}
