package sample.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.app.database.DatabaseHandler;
import sample.app.product.Product;
import sample.cash_register.CashRegisterTask;
import sample.transaction.Transaction;

public class Context {
    public DatabaseHandler db;
    private static Context instance;

    // TODO make helper class that updates database entry whenever value of Product object is changed!
    // don't make direct calls to Product methods!!! (or update database inside it's methods?)
    private ObservableList<Product> inventoryProducts;
    private CashRegisterTask register1;

    // TODO store observable lists of cash registers here?
    // make this class the hub of observable list for easy synchronization with database?

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    private Context() {
    }

    public CashRegisterTask getRegister1() {
        return register1;
    }

    public void init() {
        db = DatabaseHandler.getInstance();
        db.connect();

        initData();
        initInventory();
        initCashRegisters();
//        initTransactions();
    }

    private void initData() {
        inventoryProducts = FXCollections.observableArrayList();
    }

    private void initInventory() {
        inventoryProducts.addAll(db.getProductArrayList());
    }

    private void initCashRegisters() {
        register1 = new CashRegisterTask(this);
        Thread thread = new Thread(register1);
        thread.setDaemon(true);
        thread.start();
        // TODO start registers here; tasks?
    }

    public ObservableList<Product> getInventoryProducts() {
        return inventoryProducts;
    }

    public void update(Product property) {
        db.update(property);
    }

    public synchronized int getNextTransactionId() {
        return 0;
    }

    public void submitTransaction(Transaction transaction) {
    }
}
