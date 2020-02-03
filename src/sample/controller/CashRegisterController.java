package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import sample.app.Context;
import sample.cash_register.CashRegisterHelper;
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
    public Label cashierName1;
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
    public Label transactionTotal1;
    //endregion

    private Context context;

    @FXML
    private void initialize() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Navbar.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NavbarController navbarController = loader.getController();
        navbarController.disableButton(location.toString());
        borderPane.setTop(loader.getRoot());

        context = Context.getInstance();
        CashRegisterHelper helper = new CashRegisterHelper();
        CashRegisterTask register1 = context.getRegister1();

        transactionProductLog1.setItems(register1.getTransactionProductList());
        transactionIdCol1.setCellValueFactory(new PropertyValueFactory<>("productRow"));
        transactionProductCol1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        transactionQtyCol1.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        transactionPriceCol1.setCellValueFactory(new PropertyValueFactory<>("price"));

        cashRegisterId1.textProperty().bind(register1.cashRegisterIdProperty());
        cashierName1.textProperty().bind(register1.cashierNameProperty());
        transactionsCount1.textProperty().bind(register1.transactionCountProperty().asString());
        currentTransactionId1.textProperty().bind(register1.currentTransactionIdProperty().asString());
        transactionTotal1.textProperty().bind(register1.totalCostProperty());
    }
}
