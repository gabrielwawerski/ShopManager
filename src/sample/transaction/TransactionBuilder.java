package sample.transaction;

import sample.app.product.Product;
import sample.cash_register.CashRegisterHelper;
import sample.cash_register.CashRegisterProperty;
import sample.util.Util;

public final class TransactionBuilder {
    private ProductLog productLog;
    private double totalCost = 0;
    private String date;
    private int productRow = 1;

    public TransactionBuilder() {
        productLog = new ProductLog();
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