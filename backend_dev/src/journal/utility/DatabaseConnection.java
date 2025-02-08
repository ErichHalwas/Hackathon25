package journal.program;

import java.sql.*;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
            System.exit(1);
        }
    }

    public void createTable() throws SQLException {
        File fileName = new File("txt_files\\createTable.txt");
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Scanner scanner = new Scanner(fileName);
            
            String sql = "USE " + databaseName;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            StringBuilder sqlBuilder = new StringBuilder();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    sqlBuilder.append(line).append(" ");
                }
                if (line.endsWith(";")) { 
                    statement = connection.createStatement();
                    statement.executeUpdate(sqlBuilder.toString());
                    sqlBuilder.setLength(0);
                }
            }
            scanner.close();
            JOptionPane.showMessageDialog(null, "Tables created successfully");
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.getMessage());
            System.exit(1);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, fnfe.getMessage());
            System.exit(2);
        }
    }
/** 
    public void insertData() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection
        }
    }
*/
}