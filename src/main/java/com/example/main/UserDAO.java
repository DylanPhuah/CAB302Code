package com.example.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute("CREATE TABLE IF NOT EXISTS users ("
                                  + "username VARCHAR PRIMARY KEY, "
                                  + "password VARCHAR NOT NULL, "
                                  + "fName VARCHAR NOT NULL, "
                                  + "lName VARCHAR NOT NULL, "
                                  + "isTeacher BOOLEAN DEFAULT 0)" );
        } catch (SQLException ex) {
            System.err.println("user table creation error");
        }
    }

    public void insert(User user) {
        try {
            PreparedStatement insertUser = connection.prepareStatement(
                    "INSERT OR IGNORE INTO users (username, password, fName, lName, isTeacher) " +
                            "VALUES (?, ?, ?, ?, ?)" );
            insertUser.setString(1, user.GetUsername());
            insertUser.setString(2, user.GetPassword());
            insertUser.setString(3, user.GetFName());
            insertUser.setString(4, user.GetLName());
            insertUser.setBoolean(5, user.GetIsTeacher());
            insertUser.execute();
        } catch (SQLException ex) {
            System.err.println("user insertion error");
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(new User(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getBoolean("isTeacher"))
                );
            }
        } catch (SQLException ex) {
            System.err.println("user get all error");
        }
        return users;
    }

    public void ChangeName(User user, String NewName)
    {
        String updateQuery = "UPDATE users SET fName = ? WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, NewName);
            preparedStatement.setString(2, user.GetUsername());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("First name update failure");
        }
    }
    public void deleteUser(User user)
    {
        String deleteQuery = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, user.GetUsername());  // The ID or condition for deletion

            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.err.println("User delete failure");
        }
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
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getBoolean("isTeacher"));
            }
        } catch (SQLException ex) {
            System.err.println("user get by user error");
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("user connection close error");
        }
    }
}