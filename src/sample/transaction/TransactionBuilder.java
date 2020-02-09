package sample.transaction;

import sample.app.product.Product;
import sample.cash_register.CashRegisterHelper;
import sample.cash_register.CashRegisterProperty;
import sample.util.Util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionBuilder {
    private ProductLog productLog;
    private double totalCost = 0;
    private int productRow = 1;
    private Format formatter;

    public TransactionBuilder() {
        productLog = new ProductLog();
        formatter = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setProductLog(productLog);
        transaction.setCost(totalCost);
        transaction.setDate(getDate());
        return transaction;
    }

    public CashRegisterProperty productScan() {
        Product dbProduct = CashRegisterHelper.randomProduct();
        String name = dbProduct.getName();
        int quantity = randomQuantity();
        double price = dbProduct.getPrice();

        SingleProduct scannedProduct = SingleProduct.fromProduct(dbProduct);
        updateTotalCost(quantity, price);

        if (!productLog.contains(scannedProduct)) {
            productLog.add(scannedProduct);
            return new CashRegisterProperty(productRow++, name, quantity, price);
        } else {
            productLog.add(scannedProduct);
            return new CashRegisterProperty(productRow, name, quantity, price);
        }
    }

    public void reset() {
        productLog = new ProductLog();
        totalCost = 0;
        productRow = 1;
    }

    private String getDate() {
        return formatter.format(new Date());
    }

    private void updateTotalCost(int productQuantity, double productPrice) {
        totalCost += productPrice * productQuantity; // TODO check if correctly calculates total cost
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