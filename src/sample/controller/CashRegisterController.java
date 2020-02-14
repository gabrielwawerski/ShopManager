package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import sample.app.Context;
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
    public Label transactionTotal1;

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
    //endregion

    //region Register 2
    @FXML
    public Label cashRegisterId2;
    @FXML
    public Label cashierName2;
    @FXML
    public Label transactionsCount2;
    @FXML
    public Label currentTransactionId2;
    @FXML
    public Label transactionTotal2;

    @FXML
    public TableView<CashRegisterProperty> transactionProductLog2;
    @FXML
    public TableColumn<CashRegisterProperty, Integer> transactionIdCol2;
    @FXML
    public TableColumn<CashRegisterProperty, String> transactionProductCol2;
    @FXML
    public TableColumn<CashRegisterProperty, Integer> transactionQtyCol2;
    @FXML
    public TableColumn<CashRegisterProperty, Double> transactionPriceCol2;
    //endregion

    //region Register 3
    @FXML
    public Label cashRegisterId3;
    @FXML
    public Label cashierName3;
    @FXML
    public Label transactionsCount3;
    @FXML
    public Label currentTransactionId3;
    @FXML
    public Label transactionTotal3;

    @FXML
    public TableView<CashRegisterProperty> transactionProductLog3;
    @FXML
    public TableColumn<CashRegisterProperty, Integer> transactionIdCol3;
    @FXML
    public TableColumn<CashRegisterProperty, String> transactionProductCol3;
    @FXML
    public TableColumn<CashRegisterProperty, Integer> transactionQtyCol3;
    @FXML
    public TableColumn<CashRegisterProperty, Double> transactionPriceCol3;
    //endregion

    //region Register 4
    @FXML
    public Label cashRegisterId4;
    @FXML
    public Label cashierName4;
    @FXML
    public Label transactionsCount4;
    @FXML
    public Label currentTransactionId4;
    @FXML
    public Label transactionTotal4;

    @FXML
    public TableView<CashRegisterProperty> transactionProductLog4;
    @FXML
    public TableColumn<CashRegisterProperty, Integer> transactionIdCol4;
    @FXML
    public TableColumn<CashRegisterProperty, String> transactionProductCol4;
    @FXML
    public TableColumn<CashRegisterProperty, Integer> transactionQtyCol4;
    @FXML
    public TableColumn<CashRegisterProperty, Double> transactionPriceCol4;
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
        CashRegisterTask register1 = context.getRegister1();
        CashRegisterTask register2 = context.getRegister2();
        CashRegisterTask register3 = context.getRegister3();
        CashRegisterTask register4 = context.getRegister4();

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


        transactionProductLog2.setItems(register2.getTransactionProductList());
        transactionIdCol2.setCellValueFactory(new PropertyValueFactory<>("productRow"));
        transactionProductCol2.setCellValueFactory(new PropertyValueFactory<>("productName"));
        transactionQtyCol2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        transactionPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));

        cashRegisterId2.textProperty().bind(register2.cashRegisterIdProperty());
        cashierName2.textProperty().bind(register2.cashierNameProperty());
        transactionsCount2.textProperty().bind(register2.transactionCountProperty().asString());
        currentTransactionId2.textProperty().bind(register2.currentTransactionIdProperty().asString());
        transactionTotal2.textProperty().bind(register2.totalCostProperty());


        transactionProductLog3.setItems(register3.getTransactionProductList());
        transactionIdCol3.setCellValueFactory(new PropertyValueFactory<>("productRow"));
        transactionProductCol3.setCellValueFactory(new PropertyValueFactory<>("productName"));
        transactionQtyCol3.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        transactionPriceCol3.setCellValueFactory(new PropertyValueFactory<>("price"));

        cashRegisterId3.textProperty().bind(register3.cashRegisterIdProperty());
        cashierName3.textProperty().bind(register3.cashierNameProperty());
        transactionsCount3.textProperty().bind(register3.transactionCountProperty().asString());
        currentTransactionId3.textProperty().bind(register3.currentTransactionIdProperty().asString());
        transactionTotal3.textProperty().bind(register3.totalCostProperty());


        transactionProductLog4.setItems(register4.getTransactionProductList());
        transactionIdCol4.setCellValueFactory(new PropertyValueFactory<>("productRow"));
        transactionProductCol4.setCellValueFactory(new PropertyValueFactory<>("productName"));
        transactionQtyCol4.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        transactionPriceCol4.setCellValueFactory(new PropertyValueFactory<>("price"));

        cashRegisterId4.textProperty().bind(register4.cashRegisterIdProperty());
        cashierName4.textProperty().bind(register4.cashierNameProperty());
        transactionsCount4.textProperty().bind(register4.transactionCountProperty().asString());
        currentTransactionId4.textProperty().bind(register4.currentTransactionIdProperty().asString());
        transactionTotal4.textProperty().bind(register4.totalCostProperty());
    }
}
