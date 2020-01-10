package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.database.DatabaseConnection;
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

    private boolean isConnected = true;
    private DatabaseHandler db = new DatabaseHandler();

    public void handleInventoryButton(ActionEvent actionEvent) {
        db.test();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inventory.fxml"));
            Stage stage = (Stage) inventoryButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleCashRegistersButton(ActionEvent actionEvent) {
    }

    public void handleTransactionsButton(ActionEvent actionEvent) {
    }

    public void handleDatabaseButton(ActionEvent actionEvent) {
        if (isConnected) {
            databaseButton.setText("Connect DB");
            db.close();
            isConnected = false;
        } else {
            databaseButton.setText("Disconnect DB");
            db.connect();
            isConnected = true;
        }
    }
}
