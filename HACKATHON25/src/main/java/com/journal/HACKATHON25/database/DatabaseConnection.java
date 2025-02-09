package com.journal.HACKATHON25.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public final class DatabaseConnection {
    private final String url;
    private final String username;
    private final String password;
    private final String user; 
    private final String pass;

    public DatabaseConnection(String user, String pass) {
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$";
        this.user = user;
        this.pass = pass;   
        insertData();
    }

    public void insertData() {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(this.user + " " + this.pass);
            preparedStatement.setString(1, this.user);
            preparedStatement.setString(2, this.pass);
            preparedStatement.executeUpdate();
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
