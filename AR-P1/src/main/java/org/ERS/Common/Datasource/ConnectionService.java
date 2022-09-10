package org.ERS.Common.Datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionService {

    private static ConnectionService connFactory;
    private Properties dbProps = new Properties();

    public ConnectionService() {
        try {
            Class.forName("org.postgresql.Driver");
            dbProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("connection.properties"));
        } catch (IOException e) {
            // TODO replace RuntimeException with a custom exception
            throw new RuntimeException("Could not read from properties file.", e);
        } catch (ClassNotFoundException e) {
            // TODO replace RuntimeException with a custom exception
            throw new RuntimeException("Failed to load PostgreSQL JDBC driver.", e);
        }
    }

    public static ConnectionService getInstance() {
        if (connFactory == null) {
            connFactory = new ConnectionService();
        }
        return connFactory;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbProps.getProperty("db-url"), dbProps.getProperty("db-username"), dbProps.getProperty("db-password"));
    }

}

