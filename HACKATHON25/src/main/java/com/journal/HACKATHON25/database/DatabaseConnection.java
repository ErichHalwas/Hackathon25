package com.journal.HACKATHON25.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseConnection {
    public final String url;
    public final String username;
    public final String password;
    public String currentUser = " "; 
    private final String pass;
    private String title;
    private String text;
    private int userId;
    private boolean authenticate;

    public DatabaseConnection(String currentUser, String pass) {
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$";
        this.currentUser = currentUser;
        this.pass = pass;   
        insertDataUsers();
    }

    public DatabaseConnection(String currentUser, String pass, boolean authenticate) {
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$";
        this.currentUser = currentUser;
        this.pass = pass;
        this.authenticate = authenticate;
    }

    public DatabaseConnection(String currentUser, String title, String text) {
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$"; 
        this.currentUser = currentUser;
        this.pass = null;
        this.title = title;
        this.text = text;
        getUserID();
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

    public void getUserID() {
        String sql = "SELECT * FROM users WHERE username = '" + this.currentUser + "'";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                this.userId = resultSet.getInt("user_id");
                System.out.println("User ID: " + this.userId);
            }
               
        } catch (SQLException e) {
            throw new IllegalStateException("Error inserting data: " + e.getMessage());
        }

    }
    public void insertDataJournal() {
        
        String sql = "INSERT INTO journal_entries (user_id, title, text) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(this.userId + " " + this.title + " " + this.text);
            preparedStatement.setInt(1, this.userId);
            preparedStatement.setString(2, this.title);
            preparedStatement.setString(3, this.text);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data inserted successfully");
        } catch (SQLException e) {
            throw new IllegalStateException("Error inserting data: " + e.getMessage());
        }
    }

    public void updateJournal() {
        String sql = "UPDATE journal_entries SET title = ?, text = ? WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("Updating ID: " + this.userId + " with Title: " + this.title + " and Content: " + this.text);
            preparedStatement.setInt(1, this.userId);
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

    public boolean authenticateUser() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, this.currentUser);
            preparedStatement.setString(2, this.pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                this.authenticate = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.authenticate;
    }

}
