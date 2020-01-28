package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import sample.cash_register.bundle.BundleBuilder;
import sample.cash_register.bundle.CashRegisterBundle;
import sample.cash_register.CashRegisterProperty;
import sample.cash_register.CashRegisterTask;

import java.io.IOException;
import java.net.URL;

public class CashRegisterController {
    @FXML
    public BorderPane borderPane;
    @FXML
    public URL location;

    //region Register 1
    @FXML
    public Label cashRegisterId1;
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
    public TableView<CashRegisterProperty> transactionProductLog1;
    @FXML
    public TableColumn<CashRegisterProperty, Integer> transactionIdCol1;
    @FXML
    public TableColumn<CashRegisterProperty, String> transactionProductCol1;
    @FXML
    public TableColumn<CashRegisterProperty, Integer> transactionQtyCol1;
    @FXML
    public TableColumn<CashRegisterProperty, Double> transactionPriceCol1;
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

        CashRegisterBundle bundle = new BundleBuilder(cashRegisterId1)
                .cashierName(cashierName1)
                .transactionCount(transactionsCount1)
                .currentTransactionId(currentTransactionId1)
                .subtotalCost(transactionSubtotal1)
                .tax(transactionTax1)
                .totalCost(transactionTotal1)
                .build();


        CashRegisterTask task = new CashRegisterTask();

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
