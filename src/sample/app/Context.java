package sample.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.app.database.DatabaseHandler;
import sample.app.product.Product;
import sample.transaction.Transaction;

public class Context {
    public DatabaseHandler db;
    private static Context instance;

    // TODO make helper class that updates database entry whenever value of Product object is changed!
    // don't make direct calls to Product methods!!! (or update database inside it's methods?)
    private ObservableList<Product> inventoryProducts;

//    private ObservableList<CashRegisterProperty> cashRegisterEntries;

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
        initCashRegisters();
//        initTransactions();
    }

    private void initData() {
        inventoryProducts = FXCollections.observableArrayList();
        db = DatabaseHandler.getInstance();
        db.connect();
    }

    private void initInventory() {
        inventoryProducts.addAll(db.getProductArrayList());
    }

    private void initCashRegisters() {
        // TODO start registers here; tasks?
    }

    public ObservableList<Product> getInventoryProducts() {
        return inventoryProducts;
    }

    public void update(Product property) {
        db.update(property);
    }

    public synchronized int nextTransactionId() {
        return 0;
    }

    public void submitTransaction(Transaction transaction) {
    }
}
