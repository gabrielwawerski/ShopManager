package sample.product;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "products")
public class Product {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(unique = true)
    private String name;
    @DatabaseField
    private int quantity;
    @DatabaseField
    private double price;

    public static final Product EMPTY_PRODUCT = new Product(999999);

    public Product() {
    }

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    private Product(int id) {
        this.id = id;
        name = "";
        quantity = 0;
        price = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFullInfo() {
        return getId() + "  " + getName() + "  " + getQuantity() + "  " + getPrice();
    }
}