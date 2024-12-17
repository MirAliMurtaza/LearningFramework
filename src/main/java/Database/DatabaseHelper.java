package Database;

import java.sql.*;

import static Database.DatabaseConnection.getConnection;

public class DatabaseHelper {

    // Method to execute SELECT queries
    public static void fetchData() {
        String query = "SELECT * FROM seleniumuser"; // SQL query to fetch data
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Loop through the result set and print the data
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String email = rs.getString("email");
                System.out.println(" Name: " + firstname + ", Email: " + email);
            }

        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    public static void deleteRow(String firstname) {
        String query = "DELETE FROM seleniumuser WHERE firstname = 'TEST1'"; // SQL query to delete data

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Execute delete query
            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Row with firstname " + firstname +  "deleted successfully.");
            } else {
                System.out.println("No row found with firstname " + firstname + "to delete.");
            }

        } catch (SQLException e) {
            System.out.println("Error executing delete query: " + e.getMessage());
        }
    }

    // Method to insert data into the database
    public static void insertData(String sql) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);  // Execute the insert query
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}