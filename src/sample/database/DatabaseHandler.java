package sample.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import sample.product.Product;
import sample.product.ProductConverter;
import sample.product.ProductProperty;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseHandler {
    private static DatabaseConnection db;
    private static Dao<Product, Integer> productDao;

    private static DatabaseHandler instance;

    private DatabaseHandler() {
    }

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
            db = new DatabaseConnection();
            try {
                productDao = DaoManager.createDao(db.getConnectionSource(), Product.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void update(Product product) {
        try {
            productDao.update(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refresh(Product product) {
        try {
            productDao.refresh(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getProductArrayList() {
        ArrayList<Product> products = new ArrayList<>();

        for (Product x : productDao) {
            products.add(x);
        }
        return products;
    }


    public Product getDatabaseObject(Product product) {
        // TODO refactor to recommended way to do this (from ormlite pdf)
        for (Product x : productDao) {
            if (x.getId() == product.getId()) {
                return x;
            }
        }
        throw new IllegalArgumentException(); // TODO find better exception? add text
    }

    public Product getDatabaseObject(ProductProperty prodProperty) {
        Product product = ProductConverter.toProduct(prodProperty);

        for (Product x : productDao) {
            if (x.getId() == product.getId()) {
                return x;
            }
        }
        return Product.EMPTY_PRODUCT;
    }

    public void close() {
        db.close();
        System.out.println("Disconnected from database.");
    }

    public void connect() {
        db.connect();
        System.out.println("Connected to database.");
    }

    public void test() {
        Product apple = new Product("Apple", 15, 0.49);
        Product kiszki = new Product("Krokiety", 9, 2.29);
        try {
//            TableUtils.createTable(connectionSource, Product.class);
//            productDao.create(apple);
//            productDao.create(kiszki);
            Product retrievedProduct = productDao.queryForId(1);
            System.out.println(retrievedProduct.getFullInfo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
