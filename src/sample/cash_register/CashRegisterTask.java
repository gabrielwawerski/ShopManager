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

public class CashRegisterTask extends Task<Void> {
    private ObservableList<CashRegisterProperty> transactionProductList;

    private int id;
    private static int _id = 1;
    private double _totalCost;

    private final Context context;
    private TransactionBuilder builder;
    private DecimalFormat formatter;

    private StringProperty cashRegisterId;
    private StringProperty cashierName;
    private IntegerProperty transactionCount;
    private IntegerProperty currentTransactionId;
    private StringProperty totalCost;

    // thread sleep limits for when transaction is not yet completed
    private static final int MIN_SLEEP_ONGOING = 500;
    private static final int MAX_SLEEP_ONGOING = 2000;

    // ...when transaction has been completed
    private static final int MIN_SLEEP_FINISHED = 2000;
    private static final int MAX_SLEEP_FINISHED = 5000;

    private static final int MIN_SLEEP_AWAITING = 1000;
    private static final int MAX_SLEEP_AWAITING = 5000;

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

            // Check if product was already scanned. If it was, update it's quantity and price
            for (CashRegisterProperty x : transactionProductList) {
                if (x.getProductName().equals(scannedProduct.getProductName())) {
                    Platform.runLater(() -> {
                        x.setQuantity(x.getQuantity() + scannedProduct.getQuantity());
                        CashRegisterHelper.updateProductPrice(x);
                    });
                    wasAlreadyScanned = true;
                    System.out.println("UPDATED: " + scannedProduct.getProductName() + " +" + scannedProduct.getQuantity());
                }
            }
            if (!wasAlreadyScanned) {
                transactionProductList.add(scannedProduct);
                System.out.println("SCANNED: " + scannedProduct.getProductName());
            }

            Platform.runLater(() -> {
                updateTotalCost(scannedProduct.getQuantity() * Double.parseDouble(scannedProduct.getPrice()));
            });

            sleep(MIN_SLEEP_ONGOING, MAX_SLEEP_ONGOING);

            if (Util.random(0, 20) > 15) {
                Transaction transaction = builder.build();
                context.submitTransaction(transaction);

                builder.reset();
                sleep(MIN_SLEEP_FINISHED, MAX_SLEEP_FINISHED);
                Platform.runLater(() -> {
                    setTotalCost("PAID");
                });
                sleep(MIN_SLEEP_AWAITING, MAX_SLEEP_AWAITING);
                prepareRegister();
                sleep(500, 500);
            }

        }
        return null;
    }

    private void resetTotalCost() {
        Platform.runLater(() -> setTotalCost(""));
        _totalCost = 0;
    }

    // TODO fix - sometimes resets twice

    private void prepareRegister() {
        resetTotalCost();
        transactionProductList.clear();
        Platform.runLater(() -> {
            setTransactionCount(getTransactionCount() + 1);
            setCurrentTransactionId(context.getNextTransactionId());
        });
        System.out.println("REGISTER " + getId() + " PREPARED!");
    }

    private void updateTotalCost(double value) {
        _totalCost += value;
        setTotalCost(formatter.format(_totalCost) + " zł");
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

    private void init() {
        initProperties();

        transactionProductList = FXCollections.observableArrayList();
        formatter = new DecimalFormat("#.##");
        _totalCost = 0;
        String cashierName = CashRegisterHelper.randomCashierName();

        Platform.runLater(() -> {
            setCashRegisterId("Register " + getId());
            setCashierName(cashierName);
            setTransactionCount(0);
            setCurrentTransactionId(context.nextTransactionId());
        });
        builder = new TransactionBuilder(cashierName);
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

    public ObservableList<CashRegisterProperty> getTransactionProductList() {
        return transactionProductList;
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
