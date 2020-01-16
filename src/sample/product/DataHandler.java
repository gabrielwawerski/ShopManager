package sample.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DataHandler {
    private static volatile DataHandler instance;

    final ObservableList<ProductProperty> data;

    public static DataHandler getInstance() {
        if (instance == null) {
            synchronized (DataHandler.class) {
                if (instance == null) {
//                    instance = new DataHandler();
                }
            }
        }
        return instance;
    }

    private DataHandler(ArrayList<ProductProperty> properties) {
        data = FXCollections.observableArrayList(properties);
    }

    public ObservableList<ProductProperty> getData() {
        return data;
    }
}
