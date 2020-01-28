package sample.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.app.task.InitTransactionPropertiesTask;
import sample.database.DatabaseHandler;
import sample.product.Product;
import sample.transaction.TransactionLogProperty;

public class Context {
    public DatabaseHandler db;
    private static Context instance;

    // TODO make helper class that updates database entry whenever value of Product object is changed!
    // don't make direct calls to Product methods!!! (or update database inside it's methods?)
    private ObservableList<Product> inventoryProducts;
    private ObservableList<TransactionLogProperty> transactionProperties;

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    private Context() {
    }

    public void init() {
        initData();
        initInventory();
//        initTransactions();
    }

    private void initData() {
        inventoryProducts = FXCollections.observableArrayList();
        transactionProperties = FXCollections.observableArrayList();
        db = DatabaseHandler.getInstance();
        db.connect();
    }

    private void initInventory() {
        inventoryProducts = FXCollections.observableArrayList(db.getProductArrayList());
    }

    private void initTransactions() {
        InitTransactionPropertiesTask initTransactionsTask
                = new InitTransactionPropertiesTask(transactionProperties, db.getTransactionArrayList());

        Thread initTransactionsThread = new Thread(initTransactionsTask);
        initTransactionsThread.setDaemon(true);
        initTransactionsThread.start();
    }

    public ObservableList<Product> getInventoryProducts() {
        return inventoryProducts;
    }

    public void update(Product property) {
        db.update(property);
    }
}
