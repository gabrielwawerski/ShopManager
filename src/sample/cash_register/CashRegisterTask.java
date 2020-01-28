package sample.cash_register;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import sample.cash_register.bundle.CashRegisterBundle;
import sample.transaction.TransactionBuilder;

// 4 kasy - kazda oddzielny watek?
public class CashRegisterTask extends Task<Void> {
    private Label cashRegisterId;
    private Label cashierName;
    private Label transactionCount;
    private Label currentTransactionId;
    private Label subtotalCost;
    private Label tax;
    private Label totalCost;

    public CashRegisterTask(CashRegisterBundle cashRegisterBundle) {
        cashRegisterId = cashRegisterBundle.getCashRegisterId();
        cashierName = cashRegisterBundle.getCashierName();
        transactionCount = cashRegisterBundle.getTransactionCount();
        currentTransactionId = cashRegisterBundle.getCurrentTransactionId();
        subtotalCost = cashRegisterBundle.getSubtotalCost();
        tax = cashRegisterBundle.getTax();
        totalCost = cashRegisterBundle.getTotalCost();
    }

    private void init() {
        TransactionBuilder builder = new TransactionBuilder();

        Platform.runLater(() -> {
            cashierName.setText(CashRegisterHelper.randomCashierName());
            transactionCount.setText("0");
        });
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
}
