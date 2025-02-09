package com.journal.HACKATHON25.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public final class DatabaseConnection {
    private final String databaseName;
    private final String url;
    private final String username;
    private final String password;
    private String user, pass;

    public DatabaseConnection(String user, String pass) {
        databaseName = "journal_app";
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$";
        this.user = user;
        this.pass = pass;
        System.out.println("Database connection successful");
        insertData();
        System.out.println("Database connection successful");
    }
/** 
    public void createDatabase() {
        try {
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
            }
            JOptionPane.showMessageDialog(null, "Database created successfully");
        } catch (SQLException e) {
            throw new IllegalStateException("Error creating database: " + e.getMessage()); 
        }
        System.out.println("Database created successfully");
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public void createTable() {
        File fileName = new File("createTable.txt");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Scanner scanner = new Scanner(fileName);
            Statement statement = connection.createStatement();
            StringBuilder sqlBuilder = new StringBuilder();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    sqlBuilder.append(line).append(" ");
                }
                if (line.endsWith(";")) {
                    statement.executeUpdate(sqlBuilder.toString().trim());
                    sqlBuilder.setLength(0);
                }      
            }
            scanner.close();
            JOptionPane.showMessageDialog(null, "Table created successfully");    
        } catch (SQLException e) {
            throw new IllegalStateException("Error creating table: " + e.getMessage());
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Error reading file: " + e.getMessage());
        }
        System.out.println("Table created successfully");
    }
    */

    public void insertData() {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        System.out.println("Inserting data");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
        System.out.println("Inserting data");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("Inserting data");
        System.out.println(this.user + " " + this.pass);
        preparedStatement.setString(1, this.user);
        System.out.println("Inserting data");
        preparedStatement.setString(2, this.pass);
        System.out.println("Inserting data");
        preparedStatement.executeUpdate();
        System.out.println("Inserting data");
        JOptionPane.showMessageDialog(null, "Data inserted successfully");
        } catch (SQLException e) {
            throw new IllegalStateException("Error inserting data: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            DriverManager.getConnection(url, username, password).close();
        } catch (SQLException e) {
            throw new IllegalStateException("Error closing connection: " + e.getMessage());
        }
    }

}
