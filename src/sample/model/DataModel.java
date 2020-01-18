package sample.model;

import javafx.collections.ObservableList;
import sample.database.DatabaseHandler;
import sample.product.ProductProperty;

public class DataModel {
    private DatabaseHandler db;
    ObservableList<ProductProperty> data;

    public DataModel() {
        db = DatabaseHandler.getInstance();
        db.connect();
    }

    public ObservableList<ProductProperty> getData() {
        return data;
    }
}
