package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.transaction.TransactionEntry;

import java.io.IOException;

public class CashRegistersController {
    @FXML
    public Button backButton;
    @FXML
    public Button inventoryButton;

    //region Register 1
    @FXML
    public Label cashierLabel1;
    @FXML
    public Label cashierName1;
    @FXML
    public Label transactionsLabel1;
    @FXML
    public Label transactionsCount1;
    @FXML
    public Label currentTransactionId1;
    @FXML
    public TableView<TransactionEntry> currentTransactionTable1;
    @FXML
    public TableColumn<TransactionEntry, Integer> transactionIdCol1;
    @FXML
    public TableColumn<TransactionEntry, String> transactionProductCol1;
    @FXML
    public TableColumn<TransactionEntry, Integer> transactionQtyCol1;
    @FXML
    public TableColumn<TransactionEntry, Double> transactionPriceCol1;
    @FXML
    public Label transactionSubtotal1;
    @FXML
    public Label transactionTax1;
    @FXML
    public Label transactionTotal1;
    //endregion

    @FXML
    private void initialize() {
        transactionIdCol1.setCellValueFactory(new PropertyValueFactory<>("productId"));
        transactionProductCol1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        transactionQtyCol1.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        transactionPriceCol1.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
    }

    //region Navbar
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

    public void handleInventoryButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Inventory.fxml"));
            Stage stage = (Stage) inventoryButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleTransactionsButton(ActionEvent actionEvent) {
    }
    //endregion
}
