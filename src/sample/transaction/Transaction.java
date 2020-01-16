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

    public Transaction() {
    }


}
