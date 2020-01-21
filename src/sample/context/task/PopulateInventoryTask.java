package sample.context.task;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import sample.database.DatabaseHandler;
import sample.product.Product;
import sample.product.ProductConverter;
import sample.product.ProductProperty;

import java.util.ArrayList;

public class PopulateInventoryTask extends Task<ObservableList<ProductProperty>> {
    private final ObservableList<ProductProperty> productProperties;

    public PopulateInventoryTask(ObservableList<ProductProperty> productProperties) {
        this.productProperties = productProperties;
    }

    @Override
    protected ObservableList<ProductProperty> call() throws Exception {
        final ArrayList<Product> dbProducts = DatabaseHandler.getInstance().getProductArrayList();

        for (Product dbProduct : dbProducts) {
            if (isCancelled()) {
                break;
            }
            Platform.runLater(() -> {
                productProperties.add(ProductConverter.toProperty(dbProduct));
            });
        }
        System.out.println("TASK DONE!");
        return productProperties;
    }
}
