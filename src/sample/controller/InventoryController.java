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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.database.DatabaseHandler;
import sample.product.Product;
import sample.product.ProductConverter;
import sample.product.DataHandler;
import sample.product.ProductProperty;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class InventoryController {
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
    public Button backButton;
    @FXML
    public Button addDeliveryButton;
    @FXML
    public Button editButton;
    @FXML
    public AnchorPane anchorPane;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

//        productTable.setItems(FXCollections.observableArrayList(DatabaseHandler.getInstance().getPropertyArrayList()));

        Task<ObservableList<ProductProperty>> task = new Task<ObservableList<ProductProperty>>() {
            @Override protected ObservableList<ProductProperty> call() throws Exception {
                DataHandler dataHandler = DataHandler.getInstance();
                productTable.setItems(FXCollections.observableArrayList(DatabaseHandler.getInstance().getPropertyArrayList()));

//                while (true) {
//                    for (int i = 0; i < 20; i++) {
////                        productTable.getItems().add(new ProductProperty(10 + i, "Product " + i, 99, 0.99));
//                        data.get(ThreadLocalRandom.current().nextInt(0, data.size()))
//                                .setQuantity(ThreadLocalRandom.current().nextInt(1, 100));
//                        Thread.sleep(1000);
//                    }
//                    break;
//                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        editButton.setDisable(true);
        productTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClicked);

        System.out.println("Inventory scene loaded.");
    }

    /**
     * EventHandler for enabling {@link #editButton} when a table row is selected.
     */
    EventHandler<MouseEvent> tableClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (productTable.getSelectionModel().getSelectedItem() != null) {
                editButton.setDisable(false);
            }
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
            dialogStage.setResizable(false);
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
        Product product = new Product("Pierozki Gieni", 123, 0.40);
        DatabaseHandler.getInstance().create(product);
        productTable.getItems().add(ProductConverter.toProperty(product));
    }

    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainWindow.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleCashRegistersButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CashRegisters.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleTransactionsButton(ActionEvent actionEvent) {
    }
}
