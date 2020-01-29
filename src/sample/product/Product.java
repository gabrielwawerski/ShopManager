package sample.product;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.*;

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

    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private IntegerProperty quantityProperty;
    private DoubleProperty priceProperty;

    public static final Product EMPTY_PRODUCT = new Product(999999);

    // TODO add properties here, instead of ProductProperties class??

    public Product() {
    }

    /**
     * Needs to be called when retrieving instances from database, otherwise binding won't work. Needed because of ormlite implementation.
     */
    public void init() {
        idProperty = new SimpleIntegerProperty(id);
        nameProperty = new SimpleStringProperty(name);
        quantityProperty = new SimpleIntegerProperty(quantity);
        priceProperty = new SimpleDoubleProperty(price);
    }

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;

        idProperty = new SimpleIntegerProperty(id);
        nameProperty = new SimpleStringProperty(name);
        quantityProperty = new SimpleIntegerProperty(quantity);
        priceProperty = new SimpleDoubleProperty(price);

    }

    private Product(int id) {
        this.id = id;
        name = "";
        quantity = 0;
        price = 0;

        idProperty = new SimpleIntegerProperty(id);
        nameProperty = new SimpleStringProperty("");
        quantityProperty = new SimpleIntegerProperty(0);
        priceProperty = new SimpleDoubleProperty(0);
    }

    public int getId() {
        return id;
        // TODO check if shouldn't be idProperty.get() instead
    }

    public void setId(int id) {
        this.id = id;
        setIdProperty(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setNameProperty(name);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setQuantityProperty(quantity);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        setPriceProperty(price);
    }

    public String getFullInfo() {
        return getId() + "  " + getName() + "  " + getQuantity() + "  " + getPrice();
    }

    public int getIdProperty() {
        return idProperty.get();
    }

    public IntegerProperty idPropertyProperty() {
        return idProperty;
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public String getNameProperty() {
        return nameProperty.get();
    }

    public StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public int getQuantityProperty() {
        return quantityProperty.get();
    }

    public IntegerProperty quantityPropertyProperty() {
        return quantityProperty;
    }

    public void setQuantityProperty(int quantityProperty) {
        this.quantityProperty.set(quantityProperty);
    }

    public double getPriceProperty() {
        return priceProperty.get();
    }

    public DoubleProperty pricePropertyProperty() {
        return priceProperty;
    }

    public void setPriceProperty(double priceProperty) {
        this.priceProperty.set(priceProperty);
    }
}
