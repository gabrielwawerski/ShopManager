package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.product.Product;

import java.io.IOException;

public class InventoryController {
    @FXML
    public Button backButton;
    @FXML
    public TableView<Product> productTable;
    @FXML
    public TableColumn<Product, Integer> idColumn;
    @FXML
    public TableColumn<Product, String> nameColumn;
    @FXML
    public TableColumn<Product, Integer> quantityColumn;
    @FXML
    public TableColumn<Product, Double> priceColumn;

    private final ObservableList<Product> data
            = FXCollections.observableArrayList(DatabaseHandler.getInstance().getProductArrayList());

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setItems(data);

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
