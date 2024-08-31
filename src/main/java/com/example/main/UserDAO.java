package com.example.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute("CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR PRIMARY KEY, "
                    + "password VARCHAR NOT NULL, "
                    + "isTeacher BOOLEAN DEFAULT 0"
                    + ")" );
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void insert(User user) {
        try {
            PreparedStatement insertAccount = connection.prepareStatement(
                    "INSERT OR IGNORE INTO users (username, password, isTeacher) VALUES (?, ?, ?)" );
            insertAccount.setString(1, user.username);
            insertAccount.setString(2, user.password);
            insertAccount.setBoolean(3, user.isTeacher);
            insertAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(
                        new User(rs.getString("username"),
                                rs.getString("password"),
                                rs.getBoolean("isTeacher")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return users;
    }

    public User getByUser(String user) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement getUser = connection.prepareStatement(sql);
            getUser.setString(1, user);
            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("isTeacher"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
