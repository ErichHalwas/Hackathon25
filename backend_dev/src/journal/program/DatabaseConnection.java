package journal.program;

import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private final String databaseName;
    private final String url;
    private final String userName;
    private final String password;
    
    public DatabaseConnection() {
        databaseName = "journal_app";
        url = "jdbc:mysql://localhost:3306";
        userName = "root";
        password = "Itismeak2945$";
    }
    public void createConnection() throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(url, userName, password); 

            String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
            JOptionPane.showMessageDialog(null, "Database created successfully");
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage());
        }
    }
}