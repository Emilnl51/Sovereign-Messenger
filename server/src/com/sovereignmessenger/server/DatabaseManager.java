package com.sovereignmessenger.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection dbConnection = null;
    public DatabaseManager(Connection connection) {
        this.dbConnection = connection;
    }



    public String getPasswordHashString(String userName) {
        String query = "SELECT passwordHash from users where userName = ?";
        try {
            PreparedStatement statement = dbConnection.prepareStatement(query);
            statement.setString(1, userName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString("passwordHash");
            }
        } catch (SQLException e) {
            System.err.println("Failed to load prepared statement");
            e.printStackTrace();
        }
        return null;
    }
}
