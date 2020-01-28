package sample.database;

import com.j256.ormlite.dao.CloseableWrappedIterable;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import sample.product.Product;
import sample.transaction.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseHandler {
    private static DatabaseConnection db;
    private static Dao<Product, Integer> productDao;
    private static Dao<Transaction, Integer> transactionDao;

    private static DatabaseHandler instance;

    private DatabaseHandler() {
    }

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
            db = new DatabaseConnection();
        }
        return instance;
    }

    public void create(Transaction transaction) {
        try {
            transactionDao.create(transaction);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Product product) {
        try {
            productDao.create(product);
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

    public ArrayList<Product> getProductArrayList() {
        ArrayList<Product> products = new ArrayList<>();
        CloseableWrappedIterable<Product> productDaoIterable = productDao.getWrappedIterable();

        try {
            for (Product product : productDaoIterable) {
                product.init();
                products.add(product);
            }
            return products;
        } finally {
            try {
                productDaoIterable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Product findDbProduct(Product product) {
        CloseableWrappedIterable<Product> wrappedIterable = productDao.getWrappedIterable();
        Product foundProduct = null;

        try {
            for (Product dbProduct : wrappedIterable) {
                if (dbProduct.getIdProperty() == product.getIdProperty()) {
                    foundProduct = dbProduct;
                }
            }

            if (foundProduct == null) {
                return Product.EMPTY_PRODUCT;
            } else {
                foundProduct.init();
                return foundProduct;
            }
        } finally {
            try {
                wrappedIterable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        db.close();
        System.out.println("Disconnected from database.");
    }

    public void connect() {
        db.connect();
        try {
            productDao = DaoManager.createDao(db.getConnectionSource(), Product.class);
            transactionDao = DaoManager.createDao(db.getConnectionSource(), Transaction.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to database.");
    }

    public ArrayList<Transaction> getTransactionArrayList() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        CloseableWrappedIterable<Transaction> wrappedIterable = transactionDao.getWrappedIterable();

        try {
            for (Transaction x : wrappedIterable) {
                transactions.add(x);
            }
            return transactions;
        } finally {
            try {
                wrappedIterable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initDatabase() {
//        Product apple = new Product("Apple", 15, 0.49);
//        Product krokiety = new Product("Krokiety", 9, 2.29);
//        Product ham = new Product("Ham", 29, 1.29);
//        Product milk = new Product("Milk", 40, 0.99);

//            TableUtils.createTable(connectionSource, Product.class);
//        try {
//            productDao.create(apple);
//            productDao.create(krokiety);
//            productDao.create(ham);
//            productDao.create(milk);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
