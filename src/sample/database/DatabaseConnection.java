package sample.database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.*;

public class DatabaseConnection {
    private ConnectionSource connectionSource;

    // getConnection("jdbc:h2:~/test", "sa", "");
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    protected DatabaseConnection() {
        connect();
    }

    public void connect() {
        try {
            connectionSource = new JdbcConnectionSource(DB_URL);
            ((JdbcConnectionSource) connectionSource).setUsername(DB_USER);
            ((JdbcConnectionSource) connectionSource).setPassword(DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connectionSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
