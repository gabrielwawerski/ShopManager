package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.model.DataModel;

import java.io.IOException;

public class MainWindowController {
    @FXML
    public Button inventoryButton;
    @FXML
    public Button cashRegistersButton;
    @FXML
    public Button transactionsButton;

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

    public void handleInventoryButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Inventory.fxml"));
            Stage stage = (Stage) inventoryButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleCashRegistersButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/CashRegisters.fxml"));
            Stage stage = (Stage) cashRegistersButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleTransactionsButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Transaction.fxml"));
            Stage stage = (Stage) transactionsButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
