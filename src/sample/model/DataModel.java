package sample.model;

import sample.database.DatabaseHandler;

public class DataModel {
    private DatabaseHandler db;

    private static DataModel instance;

    public static DataModel getInstance() {
        if (instance == null) {
            instance = new DataModel();
        }
        return instance;
    }

    private DataModel() {
        db = DatabaseHandler.getInstance();
        db.connect();
    }
}
