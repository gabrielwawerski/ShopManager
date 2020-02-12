
package sample.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.app.database.DatabaseHandler;
import sample.app.product.Product;
import sample.cash_register.CashRegisterTask;
import sample.transaction.ProductLog;
import sample.transaction.SingleProduct;
import sample.transaction.Transaction;

import java.util.List;

public class Context {
    public DatabaseHandler db;
    private static Context instance;

    // TODO make helper class that updates database entry whenever value of Product object is changed!
    // don't make direct calls to Product methods!!! (or update database inside it's methods?)
    private ObservableList<Product> inventoryProducts;
    private ObservableList<Transaction> transactions;

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

    public void init() {
        db = DatabaseHandler.getInstance();
        db.connect();

        initData();
        initInventory();
        initTransactions();
        initCashRegisters();
        printTransactions();
    }

    private void printTransactions() {
        for (Transaction x : transactions) {
            System.out.println("TRANSACTION: " + x.getTransactionId());
            System.out.println("ProductLog: ");
            for (SingleProduct y : x.getProductLog().get()) {
                System.out.println(y.getQuantity() + " " + y.getName() + "\n" + y.getPrice() + " zl");
            }

            System.out.println("\nTotal cost: " + x.getCost());
            System.out.println(x.getDate() + "\n");
        }
    }

    private void initData() {
        inventoryProducts = FXCollections.observableArrayList();
        transactions = FXCollections.observableArrayList();
    }

    private void initInventory() {
        inventoryProducts.addAll(db.getProductArrayList());
    }

    private void initTransactions() {
        transactions.addAll(db.getTransactionArrayList());
        initNextTransactionId();
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

    public void update(Product property) {
        db.update(property);
    }

    public synchronized int getNextTransactionId() {
        return ++nextTransactionId;
    }

    public int nextTransactionId() {
        return nextTransactionId;
    }

    private void initNextTransactionId() {
        if (transactions.size() == 0) {
            nextTransactionId = 1;
        } else {
            nextTransactionId = transactions.get(transactions.size() - 1).getTransactionId() + 1;
        }
    }

    public void submitTransaction(Transaction transaction) {
        db.create(transaction);
        updateInventory(transaction.getProductLog());
    }

    private synchronized void updateInventory(ProductLog productLog) {
        for (SingleProduct x : productLog.get()) {
            Product inventoryProduct = getProduct(x);
            System.out.println(inventoryProduct.getName() + " before: " + inventoryProduct.getQuantity());
            inventoryProduct.setQuantity(inventoryProduct.getQuantity() - x.getQuantity());
            System.out.println(inventoryProduct.getName() + " after: " + inventoryProduct.getQuantity());
            db.update(inventoryProduct);
        }
    }

    private Product getProduct(SingleProduct singleProduct) {
        for (Product x : inventoryProducts) {
            if (x.getName().equals(singleProduct.getName())) {
                return x;
            }
        }
        throw new IllegalStateException("fixme");
    }

    public ObservableList<Product> getInventoryProducts() {
        return inventoryProducts;
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
}
