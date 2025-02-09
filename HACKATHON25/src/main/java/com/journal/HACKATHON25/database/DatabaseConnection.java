package com.journal.HACKATHON25.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.journal.HACKATHON25.extract.LoginRequest;

public class DatabaseConnection {
    private final String databaseName;
    private final String url;
    private final String username;
    private final String password;

    public DatabaseConnection() {
        this.databaseName = "journal_app";
        this.url = "jdbc:mysql://localhost:3306/";
        this.username = "root";
        this.password = "Itismeak2945$";
    }

    public void createDatabase() {
        try {
            try (Connection connection = DriverManager.getConnection(url, username, password); Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
            }
            JOptionPane.showMessageDialog(null, "Database created successfully");
        } catch (SQLException e) {
            throw new IllegalStateException("Error creating database: " + e.getMessage()); 
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public void createTable() {
        File fileName = new File("createTable.txt");
        try {
            Connection connection = DriverManager.getConnection(url + "/" + databaseName, username, password);
            Scanner scanner = new Scanner(fileName);
            Statement statement = connection.createStatement();
            StringBuilder sqlBuilder = new StringBuilder();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    sqlBuilder.append(line).append(" ");
                }
                if (line.endsWith(";")) {
                    statement.executeUpdate(sqlBuilder.toString());
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
    }

    public void insertData(LoginRequest loginRequest) {
        try {
        Connection connection = DriverManager.getConnection(url + "/" + databaseName, username, password);
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO users (username, password) VALUES ('" + loginRequest.getUsername() + "', '" + loginRequest.getPassword() + "')";
        statement.executeUpdate(sql);
        JOptionPane.showMessageDialog(null, "Data inserted successfully");
        } catch (SQLException e) {
            throw new IllegalStateException("Error inserting data: " + e.getMessage());
        }
    }

}
