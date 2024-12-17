package Database;
import Utilities.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {
    static ConfigReader configReader = new ConfigReader();

    // Replace with your actual database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/seleniumDB"; // Database URL
    private static final String USER = "root";  // Database username
    private static final String PASSWORD = configReader.getProperty("DBPass");  // Database password

    // Method to establish a connection
    public static Connection getConnection() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection and return it
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}