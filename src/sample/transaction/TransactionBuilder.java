package sample.transaction;

import sample.database.DatabaseHandler;
import sample.product.Product;
import sample.transaction.single_product.SingleProduct;
import sample.util.Util;

import java.util.ArrayList;

public final class TransactionBuilder {
    private ProductLog productLog;
    private double subtotalCost;
    private double totalCost;
    private String date;

    private ArrayList<Product> databaseProducts;

    public TransactionBuilder() {
        productLog = new ProductLog();
        databaseProducts = DatabaseHandler.getInstance().getProductArrayList();
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setProductLog(productLog);
        transaction.setCost(Cost.of(subtotalCost, totalCost));
        transaction.setDate("DummyDate");

        return transaction;
    }

    public void addProduct() {
        productLog.put(randomProduct());
    }

    private SingleProduct randomProduct() {
        Product dbProduct = databaseProducts.get(Util.random(0, databaseProducts.size()));
        double price = dbProduct.getPrice();
        int quantity = randomQuantity();

        return new SingleProduct(dbProduct.getName(), quantity, price);
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