package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import sample.transaction.TransactionProperty;

import java.io.IOException;
import java.net.URL;

public class CashRegisterController {
    @FXML
    public BorderPane borderPane;
    @FXML
    public URL location;

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
    public TableView<TransactionProperty> currentTransactionTable1;
    @FXML
    public TableColumn<TransactionProperty, Integer> transactionIdCol1;
    @FXML
    public TableColumn<TransactionProperty, String> transactionProductCol1;
    @FXML
    public TableColumn<TransactionProperty, Integer> transactionQtyCol1;
    @FXML
    public TableColumn<TransactionProperty, Double> transactionPriceCol1;
    @FXML
    public Label transactionSubtotal1;
    @FXML
    public Label transactionTax1;
    @FXML
    public Label transactionTotal1;
    //endregion

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
        System.out.println(location.toString());
        navbarController.disableButton(location.toString());

        transactionIdCol1.setCellValueFactory(new PropertyValueFactory<>("productId"));
        transactionProductCol1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        transactionQtyCol1.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        transactionPriceCol1.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
    }
}
