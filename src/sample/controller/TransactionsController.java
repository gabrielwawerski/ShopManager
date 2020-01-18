package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import sample.model.DataModel;

import java.io.IOException;
import java.net.URL;

public class TransactionsController {
    @FXML
    public BorderPane borderPane;
    @FXML
    private URL location;

    private DataModel model;

    @FXML
    private void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Navbar.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setTop(loader.getRoot());
        NavbarController navbarController = loader.getController();
        navbarController.disableButton(location.toString());
    }

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model already initialized!");
        }
        this.model = model;
    }
}
