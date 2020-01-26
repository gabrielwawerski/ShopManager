package sample.transaction.util;

import sample.transaction.Cost;
import sample.transaction.ProductLog;
import sample.transaction.Transaction;
import sample.transaction.TransactionLogProperty;

// TODO RETHINK/CHECK USAGE OF THIS CLASS AND PRODUCTCONVERTER
// instead of creating new instances, select previously created properties/transactions!
public final class TransactionConverter {
    // TODO possibly needs rework
    public static Transaction toTransaction(TransactionLogProperty property, ProductLog productLog, Cost cost) {
        return new Transaction(property.getTransactionId(), property.getCashier(),
                productLog, cost,
                property.getTransactionDate());
    }

    public static TransactionLogProperty toProperty(Transaction transaction) {
        return new TransactionLogProperty(transaction.getDate(), transaction.getTransactionId(),
                transaction.getCashier(), transaction.getCost().getTotal());
    }

    private TransactionConverter() {
    }
}
