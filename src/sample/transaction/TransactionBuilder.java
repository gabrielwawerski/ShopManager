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
        int quantity = CashRegisterHelper.randomQuantity();
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

    public double getTotalCost() {
        return totalCost;
    }
}