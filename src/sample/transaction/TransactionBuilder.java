package sample.transaction;

import javafx.collections.ObservableList;
import sample.app.database.DatabaseHandler;
import sample.app.product.Product;
import sample.cash_register.CashRegisterProperty;
import sample.transaction.single_product.SingleProduct;
import sample.util.Util;

import java.util.ArrayList;

public final class TransactionBuilder {
    private ProductLog productLog;
    private double totalCost = 0;
    private String date;
    private int productRow = 1;

    private ArrayList<Product> databaseProducts;

    public TransactionBuilder() {
        productLog = new ProductLog();
        databaseProducts = DatabaseHandler.getInstance().getProductArrayList();
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setProductLog(productLog);
        transaction.setCost(calculateCost());
        transaction.setDate(getDate());

        return transaction;
    }

    public void reset() {
        productLog = new ProductLog();
        totalCost = 0;
        date = "dummy";
        productRow = 1;
    }

    private double calculateCost() {
        return 0;
    }

    private String getDate() {
        return null;
    }

    // TODO unikatowe przedmioty - po zeskanowaniu nie powinien sie powtarzac na liscie -
    // - chyba ze dodac quantity do istniejacego produktu
    public CashRegisterProperty productScan() {
        Product dbProduct = databaseProducts.get(Util.random(0, databaseProducts.size() - 1));
        String name = dbProduct.getName();
        int quantity = randomQuantity();
        double price = dbProduct.getPrice();
//
        SingleProduct scannedProduct = new SingleProduct(name, quantity, price);

        productLog.add(scannedProduct);
        
        updateTotalCost(quantity, price);

        System.out.println("scanned: " + name);
        return new CashRegisterProperty(productRow++, name, quantity, price);
    }

    private void updateTotalCost(int productQuantity, double productPrice) {
        totalCost += productPrice * productQuantity;
    }

    // TODO add pseudorandom quantity
    // e.g. more probable for client to buy more than 1 bulki, than to buy more than 1 mleko
    private int randomQuantity() {
        int quantity;
        int random = Util.random(0, 4);

        if (random >= 1) {
            quantity = 1;
        } else {
            random = Util.random(0, 10);

            if (random < 8) {
                quantity = Util.random(1, 3);
            } else {
                quantity = Util.random(1, 10);
            }
        }
        return quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }
}