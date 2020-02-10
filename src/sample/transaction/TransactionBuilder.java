package sample.transaction;

import sample.app.product.Product;
import sample.cash_register.CashRegisterHelper;
import sample.cash_register.CashRegisterProperty;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionBuilder {
    private String cashier;
    private ProductLog productLog;
    private double totalCost = 0;
    private int productRow = 1;

    private Format formatter;

    public TransactionBuilder(String cashier) {
        this.cashier = cashier;
        productLog = new ProductLog();
        formatter = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setCashier(cashier);
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
        productLog.clear();
        totalCost = 0;
        productRow = 1;
    }

    private String getDate() {
        return formatter.format(new Date());
    }

    private void updateTotalCost(int productQuantity, double productPrice) {
        totalCost += productPrice * productQuantity; // TODO check if correctly calculates total cost
        System.out.println("total cost: " + totalCost);
    }

    public double getTotalCost() {
        return totalCost;
    }
}