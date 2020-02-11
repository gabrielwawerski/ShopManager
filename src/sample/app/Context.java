package sample.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.app.database.DatabaseHandler;
import sample.app.product.Product;
import sample.cash_register.CashRegisterTask;
import sample.transaction.SingleProduct;
import sample.transaction.Transaction;

import java.util.List;

public class Context {
    public DatabaseHandler db;
    private static Context instance;

    // TODO make helper class that updates database entry whenever value of Product object is changed!
    // don't make direct calls to Product methods!!! (or update database inside it's methods?)
        private ObservableList<Product> inventoryProducts;
        
        private int nextTransactionId;

    private CashRegisterTask register1;
    private CashRegisterTask register2;
    private CashRegisterTask register3;
    private CashRegisterTask register4;

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

    public CashRegisterTask getRegister2() {
        return register2;
    }

    public CashRegisterTask getRegister3() {
        return register3;
    }

    public CashRegisterTask getRegister4() {
        return register4;
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
        setNextTransactionId();
    }

    private void initInventory() {
        inventoryProducts.addAll(db.getProductArrayList());
    }

    private void initCashRegisters() {
        register1 = new CashRegisterTask(this);
//        register2 = new CashRegisterTask(this);
//        register3 = new CashRegisterTask(this);
//        register4 = new CashRegisterTask(this);

        Thread thread1 = new Thread(register1);
//        Thread thread2 = new Thread(register2);
//        Thread thread3 = new Thread(register3);
//        Thread thread4 = new Thread(register4);

        thread1.setDaemon(true);
//        thread2.setDaemon(true);
//        thread3.setDaemon(true);
//        thread4.setDaemon(true);

        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();

        System.out.println("cash register " + register1.getId() + " initialized!");
//        System.out.println("cash register " + register2.getId() + " initialized!");
//        System.out.println("cash register " + register3.getId() + " initialized!");
//        System.out.println("cash register " + register4.getId() + " initialized!");
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
    
    private void setNextTransactionId() {
        nextTransactionId = // TODO
    }

    public void submitTransaction(Transaction transaction) {
        db.create(transaction);
        refreshData(transaction.getProductLog().get());
    }

    private void refreshData(List<Product> data) {

    }

    private void refreshData() {
        db.refreshAll(inventoryProducts);
    }
}
