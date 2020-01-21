package sample.context;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.database.DatabaseHandler;
import sample.context.task.PopulateInventoryTask;
import sample.product.ProductConverter;
import sample.product.ProductProperty;

public class Context {
    public DatabaseHandler db;
    private static Context instance;

    private ObservableList<ProductProperty> productProperties;
    private boolean inventoryInitialized;

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    private Context() {
        db = DatabaseHandler.getInstance();
        db.connect();
    }

    public void initData() {
        productProperties = FXCollections.observableArrayList();
        inventoryInitialized = false;
    }

    public void initInventory() {
        if (!isInventoryInitialized()) {
            PopulateInventoryTask task = new PopulateInventoryTask(productProperties);
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

            task.setOnSucceeded(event -> productProperties = task.getValue());
            setInventoryInitialized();
        }
    }

    public ObservableList<ProductProperty> getProductProperties() {
        return productProperties;
    }

    public void update(ProductProperty property) {
        db.update(ProductConverter.toProduct(property));
    }

    private boolean isInventoryInitialized() {
        return inventoryInitialized;
    }

    private void setInventoryInitialized() {
        inventoryInitialized = true;
    }
}
