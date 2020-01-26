package sample.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.app.task.InitTransactionPropertiesTask;
import sample.database.DatabaseHandler;
import sample.app.task.InitProductPropertiesTask;
import sample.product.ProductConverter;
import sample.product.ProductProperty;
import sample.transaction.TransactionLogProperty;

public class Context {
    public DatabaseHandler db;
    private static Context instance;

    private ObservableList<ProductProperty> productProperties;
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
        productProperties = FXCollections.observableArrayList();
        transactionProperties = FXCollections.observableArrayList();
        db = DatabaseHandler.getInstance();
        db.connect();
    }

    private void initInventory() {
        InitProductPropertiesTask initProductsTask
                = new InitProductPropertiesTask(productProperties, db.getProductArrayList());

        Thread initProductsThread = new Thread(initProductsTask);
        initProductsThread.setDaemon(true);
        initProductsThread.start();
    }

    private void initTransactions() {
        InitTransactionPropertiesTask initTransactionsTask
                = new InitTransactionPropertiesTask(transactionProperties, db.getTransactionArrayList());

        Thread initTransactionsThread = new Thread(initTransactionsTask);
        initTransactionsThread.setDaemon(true);
        initTransactionsThread.start();
    }

    public ObservableList<ProductProperty> getProductProperties() {
        return productProperties;
    }

    public void update(ProductProperty property) {
        db.update(ProductConverter.toProduct(property));
    }
}
