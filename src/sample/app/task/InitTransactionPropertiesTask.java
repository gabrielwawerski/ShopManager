package sample.app.task;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import sample.transaction.Transaction;
import sample.transaction.TransactionLogProperty;
import sample.transaction.util.TransactionConverter;

import java.util.ArrayList;

public class InitTransactionPropertiesTask extends Task<Void> {
    private final ObservableList<TransactionLogProperty> transactionProperties;
    private final ArrayList<Transaction> databaseTransactions;

    public InitTransactionPropertiesTask(ObservableList<TransactionLogProperty> transactionProperties,
                                     ArrayList<Transaction> databaseTransaction) {
        this.transactionProperties = transactionProperties;
        this.databaseTransactions = databaseTransaction;
    }

    @Override
    protected Void call() throws Exception {
        for (Transaction dbTransaction : databaseTransactions) {
            if (isCancelled()) {
                break;
            }
            Platform.runLater(() -> {
                transactionProperties.add(TransactionConverter.toProperty(dbTransaction));
            });
        }
        System.out.println("TASK DONE!");
        return null;
    }
}
