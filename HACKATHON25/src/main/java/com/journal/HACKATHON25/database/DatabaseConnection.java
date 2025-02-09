package com.journal.HACKATHON25.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DatabaseConnection {
    public final String url;
    public final String username;
    public final String password;
    public String currentUser; 
    private final String pass;
    private String title;
    private String text;

    public DatabaseConnection(String currentUser, String pass) {
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$";
        this.currentUser = currentUser;
        this.pass = pass;   
        insertDataUsers();
    }

    public DatabaseConnection(String currentUser, String title, String text) {
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$"; 
        this.pass = null;
        this.title = title;
        this.text = text;
        insertDataJournal();
    }

    public void insertDataUsers() {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(this.currentUser + " " + this.pass);
            preparedStatement.setString(1, this.currentUser);
            preparedStatement.setString(2, this.pass);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data inserted successfully");
        } catch (SQLException e) {
            throw new IllegalStateException("Error inserting data: " + e.getMessage());
        }
    }

    public void insertDataJournal() {
        String sql = "INSERT INTO journal_entries (title, content) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(this.title + " " + this.text);
            preparedStatement.setString(1, this.title);
            preparedStatement.setString(2, this.text);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data inserted successfully");
        } catch (SQLException e) {
            throw new IllegalStateException("Error inserting data: " + e.getMessage());
        }
    }

    public void updateJournal() {
        String sql = "UPDATE journal_entries SET title = ?, text = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(this.title + " " + this.text);
            preparedStatement.setString(1, this.title);
            preparedStatement.setString(2, this.text);
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
