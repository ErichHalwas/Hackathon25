package com.journal.HACKATHON25.extract;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public String username;
    public String password;

    public static final String URL = "jdbc:mysql://localhost:3066/journal_app";
    public static final String USER = "root";
    public static final String PASSWORD = "#MyPassw0rd";

    /*public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                isAuthenticated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }
}