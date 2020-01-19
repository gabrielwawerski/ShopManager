package sample.context;

import javafx.scene.control.TableView;
import sample.database.DatabaseHandler;
import sample.context.task.PopulateInventoryTask;
import sample.product.ProductConverter;
import sample.product.ProductProperty;

public class Context {
    public DatabaseHandler db;

    private static Context instance;

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

    public void populateInventory(TableView<ProductProperty> propertyTableView) {
        new PopulateInventoryTask(propertyTableView);
    }

    public void update(ProductProperty property) {
        db.update(ProductConverter.toProduct(property));
    }
}
