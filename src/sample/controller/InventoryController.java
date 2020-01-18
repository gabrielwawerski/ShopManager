package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.database.DatabaseHandler;
import sample.model.DataModel;
import sample.product.Product;
import sample.product.ProductConverter;
import sample.product.ProductProperty;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class InventoryController {
    @FXML
    public BorderPane borderPane;
    @FXML
    public Button addDeliveryButton;
    @FXML
    public Button editButton;

    @FXML
    private URL location;

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

    private DatabaseHandler db = DatabaseHandler.getInstance();
    private DataModel model;

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("Model already initialized!");
        }
        this.model = model;
    }

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

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        // editable row (not working atm)
//        priceColumn.setOnEditCommit(event -> {
//            event.getTableView().getItems().get(event.getTablePosition().getRow()).setPrice(event.getNewValue());
//        });

        Task<ObservableList<ProductProperty>> getPropertyTask = new Task<ObservableList<ProductProperty>>() {
            @Override
            protected ObservableList<ProductProperty> call() throws Exception {
                ObservableList<ProductProperty> productProperties = FXCollections.observableArrayList();
                ArrayList<Product> dbProducts = db.getProductArrayList();

                for (Product dbProduct : dbProducts) {
                    productProperties.add(ProductConverter.toProperty(dbProduct));
                }

                System.out.println("TASK DONE!");
                return productProperties;
            }
        };

        Thread thread = new Thread(getPropertyTask);
        thread.setDaemon(true);
        thread.start();

        getPropertyTask.setOnSucceeded(event -> productTable.setItems(getPropertyTask.getValue()));

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
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/EditDialog.fxml"));
            GridPane page = (GridPane) loader.load();

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

            EditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(selectedProduct);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            // check if data has changed, if it did, update database entry
            if (controller.getProduct().getPrice() != priceStamp
                    || !controller.getProduct().getName().equals(nameStamp)) {
                db.update(ProductConverter.toProduct(controller.getProduct()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAddDelivery(ActionEvent actionEvent) {
    }
}
