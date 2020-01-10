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

public class InventoryController {
    public Button backButton;

    @FXML
    private void initialize() {
        System.out.println("loaded.");
    }

    public void handleBackButton(ActionEvent actionEvent) {
        DatabaseHandler db = new DatabaseHandler();
        db.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
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
}
