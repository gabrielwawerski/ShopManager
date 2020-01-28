package sample.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.database.DatabaseHandler;
import sample.product.Product;

public class Context {
    public DatabaseHandler db;
    private static Context instance;

    // TODO make helper class that updates database entry whenever value of Product object is changed!
    // don't make direct calls to Product methods!!! (or update database inside it's methods?)
    private ObservableList<Product> inventoryProducts;

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
        db = DatabaseHandler.getInstance();
        db.connect();
    }

    private void initInventory() {
        inventoryProducts.addAll(db.getProductArrayList());
    }

    public ObservableList<Product> getInventoryProducts() {
        return inventoryProducts;
    }

    public void update(Product property) {
        db.update(property);
    }
}
