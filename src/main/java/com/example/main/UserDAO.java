package com.example.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** A database access object for the users table */
public class UserDAO {
    private final Connection connection;

    /** Instantiates a database access object for the users table */
    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /** Creates the users table in the database if it does not already exist */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute("CREATE TABLE IF NOT EXISTS users ("
                                  + "username VARCHAR PRIMARY KEY, "
                                  + "password VARCHAR NOT NULL, "
                                  + "fName VARCHAR NOT NULL, "
                                  + "lName VARCHAR NOT NULL, "
                                  + "isTeacher BOOLEAN DEFAULT 0, "
                                  + "textPreference INT DEFAULT 14)" );
        } catch (SQLException ex) {
            System.err.println("user table creation error");
        }
    }

    /**
     * Inserts a given user into the database
     * @param user The user to be inserted
     */
    public void insert(User user) {
        if(getByUser(user.GetUsername()) != null)
        {
            System.err.println("Username is already taken!");
        }
        try {
            PreparedStatement insertUser = connection.prepareStatement(
                    "INSERT OR IGNORE INTO users VALUES (?, ?, ?, ?, ?, 14)" );
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

    /**
     * Retrieves all users from the database
     * @return A list of all users in the database
     */
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
                        rs.getBoolean("isTeacher"),
                        rs.getInt("textPreference"))
                );
            }
        } catch (SQLException ex) {
            System.err.println("user get all error");
        }
        return users;
    }

    public void changeName(User user, String NewName)
    {
        user.ChangeFirstName(NewName);
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

    public void changeTextPreference(User user, int newPreference)
    {
        user.SetTextPreference(newPreference);
        String updateQuery = "UPDATE users SET textPreference = ? WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, newPreference);
            preparedStatement.setString(2, user.GetUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Text preference update failure");
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

    /**
     * Retrieves a user matching the given username
     * @param user The username to be matched
     * @return The user with the given username
     */
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
                        rs.getBoolean("isTeacher"),
                        rs.getInt("textPreference"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    /** Closes the database connection */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("user connection close error");
        }
    }
}