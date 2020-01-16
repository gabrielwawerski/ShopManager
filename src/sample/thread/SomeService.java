package sample.thread;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import sample.database.DatabaseHandler;
import sample.product.ProductProperty;

public class SomeService extends Service<ObservableList<ProductProperty>> {
    public SomeService() {
    }

    @Override
    protected Task<ObservableList<ProductProperty>> createTask() {
        return new Task<ObservableList<ProductProperty>>() {
            @Override
            protected ObservableList<ProductProperty> call() throws Exception {
                updateValue(FXCollections.observableArrayList(DatabaseHandler.getInstance().getPropertyArrayList()));
                return null;
            }
        };
    }
}
