package com.mycompany.time.time;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Change these values according to your setup
    private static final String URL = "jdbc:mysql://localhost:3306/time_conversion_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return connection;
    }
}
