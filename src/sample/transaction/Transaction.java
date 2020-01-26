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
    private ProductLog productLog;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Cost cost;
    @DatabaseField
    private String date;

    public Transaction() {
    }

    public Transaction(String cashier, ProductLog productLog, Cost cost, String date) {
        this.cashier = cashier;
        this.productLog = productLog;
        this.cost = cost;
        this.date = date;
    }

    public Transaction(int transactionId, String cashier, ProductLog productLog, Cost cost, String date) {
        this.transactionId = transactionId;
        this.cashier = cashier;
        this.productLog = productLog;
        this.cost = cost;
        this.date = date;
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

    public ProductLog getProductLog() {
        return productLog;
    }

    public void setProductLog(ProductLog productLog) {
        this.productLog = productLog;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
