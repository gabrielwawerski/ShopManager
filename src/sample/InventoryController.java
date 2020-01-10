package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.product.Product;

import java.io.IOException;

public class InventoryController {
    @FXML
    public Button backButton;
    @FXML
    public TableView<Product> productTable;

    private final ObservableList<Product> data
            = FXCollections.observableArrayList(DatabaseHandler.getInstance().getProductArrayList());

    @FXML
    private void initialize() {

        System.out.println("Inventory scene loaded.");
    }

    public void handleBackButton(ActionEvent actionEvent) {
        DatabaseHandler db = DatabaseHandler.getInstance();
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
