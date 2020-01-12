package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.product.ProductProperty;

import java.io.IOException;

public class InventoryController {
    @FXML
    public Button backButton;
    @FXML
    public TableView<ProductProperty> productTable;
    @FXML
    public TableColumn<ProductProperty, Integer> idColumn;
    @FXML
    public TableColumn<ProductProperty, String> nameColumn;
    @FXML
    public TableColumn<ProductProperty, Integer> quantityColumn;
    @FXML
    public TableColumn<ProductProperty, Double> priceColumn;
    @FXML
    public Button addDeliveryButton;
    @FXML
    public Button editButton;

    private final ObservableList<ProductProperty> data
            = FXCollections.observableArrayList(DatabaseHandler.getInstance().getPropertyArrayList());
    public AnchorPane anchorPane;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setItems(data);
        editButton.setDisable(true);

        productTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClicked);
//        anchorPane.addEventFilter(MouseEvent.MOUSE_CLICKED, backgroundClicked);

        System.out.println("Inventory scene loaded.");
    }

    EventHandler<MouseEvent> tableClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (productTable.getSelectionModel().getSelectedItem() != null) {
                editButton.setDisable(false);
            }
        }
    };

    EventHandler<MouseEvent> backgroundClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            editButton.setDisable(true);
        }
    };

    public void handleEditEntry(ActionEvent actionEvent) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/EditDialog.fxml"));
            GridPane page = (GridPane) loader.load();

            ProductProperty selectedProduct = productTable.getSelectionModel().getSelectedItem();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(Main.getStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditDialogController controller = loader.getController();

            controller.setDialogStage(dialogStage);
            controller.setProduct(selectedProduct);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAddDelivery(ActionEvent actionEvent) {
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
