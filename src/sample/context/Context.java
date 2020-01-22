package sample.context;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.database.DatabaseHandler;
import sample.context.task.InitProductPropertiesTask;
import sample.product.ProductConverter;
import sample.product.ProductProperty;

public class Context {
    public DatabaseHandler db;
    private static Context instance;

    private ObservableList<ProductProperty> productProperties;

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
    }

    private void initData() {
        productProperties = FXCollections.observableArrayList();
        db = DatabaseHandler.getInstance();
        db.connect();
    }

    private void initInventory() {
        InitProductPropertiesTask task = new InitProductPropertiesTask(productProperties, db.getProductArrayList());
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        task.setOnSucceeded(event -> productProperties = task.getValue());
    }

    public ObservableList<ProductProperty> getProductProperties() {
        return productProperties;
    }

    public void update(ProductProperty property) {
        db.update(ProductConverter.toProduct(property));
    }
}
