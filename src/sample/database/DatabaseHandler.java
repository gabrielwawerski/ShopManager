package sample.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import sample.product.Product;

import java.sql.SQLException;

public class DatabaseHandler {
    private DatabaseConnection db;
    private static Dao<Product, Integer> productDao;

    public DatabaseHandler() {
        db = DatabaseConnection.getInstance();
        try {
            productDao = DaoManager.createDao(db.getConnectionSource(), Product.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    
    public Product getProduct(Product product) {
        // TODO refactor to recommended way to do this (from ormlite pdf)
        for (Product x : productDao) {
            if (x.getId() == product.getId()) {
                return x;
            } else {
                throw new IllegalArgumentException(); // TODO find better exception? add text
            }
        }
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
