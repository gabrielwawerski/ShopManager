package sample.model.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;
import sample.database.DatabaseHandler;
import sample.product.Product;
import sample.product.ProductConverter;
import sample.product.ProductProperty;

import java.util.ArrayList;

public class PopulateProductTableTask extends Task<ObservableList<ProductProperty>> {
    public PopulateProductTableTask(TableView<ProductProperty> productTable) {
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();

        this.setOnSucceeded(event -> productTable.setItems(this.getValue()));
    }

    @Override
    protected ObservableList<ProductProperty> call() throws Exception {
        final ObservableList<ProductProperty> productProperties = FXCollections.observableArrayList();
        final ArrayList<Product> dbProducts = DatabaseHandler.getInstance().getProductArrayList();

        for (Product dbProduct : dbProducts) {
            if (isCancelled()) {
                break;
            }
            productProperties.add(ProductConverter.toProperty(dbProduct));
        }
        System.out.println("TASK DONE!");
        return productProperties;
    }
}
