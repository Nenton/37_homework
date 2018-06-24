package ru.innopolis.stc9.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection for PostgreSQL
 */
public class ConnectionManagerJDBCImpl implements ConnectionManager {

    private static ConnectionManager connectionManager;

    private ConnectionManagerJDBCImpl() {

    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerJDBCImpl();
        }
        return connectionManager;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.
                    getConnection("jdbc:postgresql://localhost:5432/Test",
                            "postgres",
                            "postgres");
        } catch (SQLException | ClassNotFoundException e) {
        }
        return connection;
    }
}
