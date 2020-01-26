package sample.controller;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.app.Context;
import sample.product.ProductProperty;

import java.io.IOException;
import java.net.URL;

public class InventoryController {
    @FXML
    public BorderPane borderPane;
    @FXML
    public Button addDeliveryButton;
    @FXML
    public Button editButton;
    @FXML
    public URL location;

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

    private Context context;

    @FXML
    private void initialize() {
        context = Context.getInstance();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Navbar.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setTop(loader.getRoot());
        NavbarController navbarController = loader.getController();
        navbarController.disableButton(location.toString());

        productTable.setItems(context.getProductProperties());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        editButton.setDisable(true);
        productTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClicked);
    }

    /**
     * EventHandler for enabling {@link #editButton} when a table row is selected.
     */
    EventHandler<MouseEvent> tableClicked = event -> {
        if (productTable.getSelectionModel().getSelectedItem() != null) {
            editButton.setDisable(false);
        }
    };

    public void handleEditEntry(ActionEvent actionEvent) {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/EditDialog.fxml"));
        GridPane page = null;
        try {
            page = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProductProperty selectedProduct = productTable.getSelectionModel().getSelectedItem();
        String nameStamp = selectedProduct.getName();
        double priceStamp = selectedProduct.getPrice();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Product");
        dialogStage.setResizable(false);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(Main.getStage());
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        EditDialogController editDialog = loader.getController();
        editDialog.setDialogStage(dialogStage);
        editDialog.setProduct(selectedProduct);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        // check if data has changed, if it did, update database entry
        if (editDialog.getProduct().getPrice() != priceStamp
                || !editDialog.getProduct().getName().equals(nameStamp)) {
            context.update(editDialog.getProduct());
        }
    }

    public void handleAddDelivery(ActionEvent actionEvent) {
    }
}
