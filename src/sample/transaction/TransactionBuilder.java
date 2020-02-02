package sample.transaction;

import sample.app.database.DatabaseHandler;
import sample.app.product.Product;
import sample.cash_register.CashRegisterProperty;
import sample.transaction.single_product.SingleProduct;
import sample.util.Util;

import java.util.ArrayList;

public final class TransactionBuilder {
    private ProductLog productLog;
    private double subtotalCost;
    private double totalCost = 0;
    private String date;
    private int productRow = 1;

    private ArrayList<Product> databaseProducts;

    public TransactionBuilder() {
        productLog = new ProductLog();
        databaseProducts = DatabaseHandler.getInstance().getProductArrayList();
    }

    public Transaction build() {
        calculateCost();
        getDate();

        Transaction transaction = new Transaction();
        transaction.setProductLog(productLog);
        transaction.setCost(Cost.of(subtotalCost, totalCost));
        transaction.setDate("DummyDate");

        return transaction;
    }

    public void reset() {
        productLog = new ProductLog();
        subtotalCost = 0;
        totalCost = 0;
        date = "dummy";
        productRow = 0;
    }

    private void calculateCost() {
        // set subtotalcost, totalcost
    }

    private void getDate() {
    }

    public CashRegisterProperty productScan() {
        Product dbProduct = databaseProducts.get(Util.random(0, databaseProducts.size()));
        String name = dbProduct.getName();
        double price = dbProduct.getPrice();
        int quantity = randomQuantity();
        productLog.put(new SingleProduct(name, quantity, price));
        updateTotalCost(price);

        return new CashRegisterProperty(productRow++, name, quantity, price);
    }

    private void updateTotalCost(double productPrice) {
        totalCost += productPrice;
    }

    private int randomQuantity() {
        int quantity;
        int random = Util.random(0, 4);

        if (random <= 1) {
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
}