package sample.transaction;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Transaction {
    @DatabaseField(generatedId = true)
    private int transactionId;
    @DatabaseField
    private String cashier;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ProductBundle productBundle;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Cost cost;
    private String date;

    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public ProductBundle getProductBundle() {
        return productBundle;
    }

    public void setProductBundle(ProductBundle productBundle) {
        this.productBundle = productBundle;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }
}
