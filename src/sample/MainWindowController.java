package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;

import java.io.IOException;

public class MainWindowController {
    @FXML
    public Button inventoryButton;
    @FXML
    public Button cashRegistersButton;
    @FXML
    public Button transactionsButton;
    @FXML
    public Button databaseButton;

    private DatabaseHandler db;

    @FXML
    private void initialize() {
        db = DatabaseHandler.getInstance();
        db.connect();
    }

    public void handleInventoryButton(ActionEvent actionEvent) {
        db.initDatabase();

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
    }
}
