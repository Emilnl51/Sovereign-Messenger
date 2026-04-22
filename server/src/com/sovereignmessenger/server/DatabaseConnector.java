package com.sovereignmessenger.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private Connection dbConnection = null;
    public DatabaseConnector() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(".env")){
            System.out.println("DEBUG: Java letar efter .env i: " + new java.io.File(".").getAbsolutePath());
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("Could not load environment variables from file");
            e.printStackTrace();
            System.exit(1);
        }
        String dbURL = prop.getProperty("DB_URL");
        String user = prop.getProperty("DB_USER");
        String password = prop.getProperty("DB_PASSWORD");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(dbURL, user, password);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return dbConnection;
    }
}
