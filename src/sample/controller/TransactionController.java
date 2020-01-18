package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import sample.model.DataModel;

public class TransactionController {
    private DataModel model;

    @FXML
    private void initialize() {
    }

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model already initialized!");
        }
        this.model = model;
    }

    public void handleBackButton(ActionEvent actionEvent) {
    }

    public void handleCashRegistersButton(ActionEvent actionEvent) {
    }

    public void handleTransactionsButton(ActionEvent actionEvent) {
    }
}
