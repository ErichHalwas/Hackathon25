package com.journal.HACKATHON25.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.journal.HACKATHON25.send.SendEntry;

public class DatabaseConnection {
    public final String url;
    public final String username;
    public final String password;
    public String currentUser = " "; 
    private final String pass;
    private String title = " ";
    private String text;
    private int userId;
    private boolean authenticate;
    private int entryId;


    public DatabaseConnection(String currentUser) {
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$";
        this.currentUser = currentUser;
        this.pass = null;
    }

    public DatabaseConnection(String currentUser, int entryId) {
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$";
        this.currentUser = currentUser;
        this.pass = null;
        this.entryId = entryId;
        getUserID();
    }

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

    public DatabaseConnection(String currentUser, String title, String text, boolean newEntry) {
        url = "jdbc:mysql://localhost:3306/journal_app";
        username = "root";
        password = "Itismeak2945$"; 
        this.currentUser = currentUser;
        this.pass = null;
        this.text = text;
        if (newEntry) {
            getUserID();
            insertDataJournal();
        } else {
            getUserID();
            getEntryID();
            this.title = title;
            updateJournal();
        }
    }
/** 
    public void deleteEntries() {
        String sql = "DELETE FROM journal_entries WHERE user_id = ? AND entry_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.userId);
            statement.setInt(2, this.entryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Error deleting data: " + e.getMessage());
        }
    }
*/
    public List<SendEntry> getAllEntries() {
        List<SendEntry> entries = new ArrayList<>();
        String sql = "SELECT * FROM journal_entries";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                entries.add(new SendEntry(resultSet.getInt("entry_id"),
                resultSet.getInt("user_id"),
                resultSet.getString("title"),
                resultSet.getString("date"),
                resultSet.getString("text")));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error sending data: " + e.getMessage());
        }
        return entries;
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

    public void getEntryID() {
        String sql;
        boolean hasTitle = (!this.title.equals(" "));
        if (hasTitle) {
            sql = "SELECT * FROM journal_entries WHERE user_id = ? AND title = ?";
        } else {
            sql = "SELECT * FROM journal_entries WHERE user_id = ? ORDER BY date DESC LIMIT 1";
        }
            try(Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.userId);
            if (hasTitle) {
                statement.setString(2, this.title);
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                this.entryId= resultSet.getInt("entry_id");
                System.out.println("Entry ID: " + this.entryId);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error saving data: " + e.getMessage());
        }
    }

    public void getUserID() {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.currentUser);
            ResultSet resultSet = statement.executeQuery();
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
            //JOptionPane.showMessageDialog(null, "Data inserted successfully");
        } catch (SQLException e) {
            throw new IllegalStateException("Error inserting data: " + e.getMessage());
        }
    }

    public void updateJournal() {
        String sql = "UPDATE journal_entries SET title = ?, text = ? WHERE user_id = ? AND entry_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("Updating ID: " + this.userId + " with Title: " + this.title + " and Content: " + this.text + " and Entry: " + this.entryId);
            preparedStatement.setInt(3, this.userId);
            preparedStatement.setString(1, this.title);
            preparedStatement.setString(2, this.text);
            preparedStatement.setInt(4, this.entryId);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data inserted successfully");
        } catch (SQLException e) {
            throw new IllegalStateException("Error inserting data: " + e.getMessage());
        }
    }
/** 
    public SendEntry getEntry() {
        SendEntry entry;
        String sql = "SELECT * FROM journal_entries WHERE user_id = ? AND entry_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, this.userId);
            statement.setInt(2, this.entryId);
            ResultSet resultSet = statement.executeQuery();
            entry = new SendEntry(resultSet.getInt("entry_id"),
                resultSet.getInt("user_id"),
                resultSet.getString("title"),
                resultSet.getString("date"),
                resultSet.getString("text"));
        } catch (SQLException e) {
            throw new IllegalStateException("Error sending data: " + e.getMessage());
        }
        return entry;
    }
        */
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
            throw new IllegalStateException("Error authenticating data: " + e.getMessage());
        }
        return this.authenticate;
    }

}
