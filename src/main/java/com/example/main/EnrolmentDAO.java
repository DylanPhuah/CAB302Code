package com.example.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** A database access object for the enrolments table */
public class EnrolmentDAO {
    private final Connection connection;

    /** Instantiates a database access object for the enrolments table */
    public EnrolmentDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /** Creates the textbooks table in the database if it does not already exist */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute("CREATE TABLE IF NOT EXISTS enrolments ("
                                  + "username VARCHAR, "
                                  + "unitCode VARCHAR, "
                                  + "PRIMARY KEY (username, unitCode), "
                                  + "FOREIGN KEY (username) REFERENCES users)" );
        } catch (SQLException ex) {
            System.err.println("enrolment table creation error");
        }
    }

    /**
     * Inserts a given enrolment into the database
     * @param enrolment The enrolment to be inserted
     */
    public void insert(Enrolment enrolment) {
        try {
            PreparedStatement insertAccount = connection.prepareStatement(
                    "INSERT OR IGNORE INTO enrolments (username, unitCode) " +
                            "VALUES (?, ?)" );
            insertAccount.setString(1, enrolment.GetUsername());
            insertAccount.setString(2, enrolment.GetUnitCode());
            insertAccount.execute();
        } catch (SQLException ex) {
            System.err.println("enrolment insertion error");
        }
    }

    /**
     * Retrieves all enrolments matching the given unit code from the database
     * @param unitCode The unit code to be matched
     * @return A list of all enrolments matching the given unit code
     */
    public List<Enrolment> getAllByUnit(String unitCode) {
        List<Enrolment> enrolments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM enrolments WHERE unitCODE = ?";
            PreparedStatement getAllByUnit = connection.prepareStatement(sql);
            getAllByUnit.setString(1, unitCode);
            ResultSet rs = getAllByUnit.executeQuery();
            while (rs.next()) {
                enrolments.add(new Enrolment(rs.getString("username"),
                        rs.getString("unitCode"))
                );
            }
        } catch (SQLException ex) {
            System.err.println("enrolment get all by unit error");
        }
        return enrolments;
    }

    public void deleteEnrolment(Enrolment enrolment)
    {
        String deleteQuery = "DELETE FROM enrolments WHERE username = ? AND unitCode = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, enrolment.GetUsername());  // The ID or condition for deletion
            preparedStatement.setString(2, enrolment.GetUnitCode());  // The ID or condition for deletion
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.err.println("User delete failure");
        }
    }

    /**
     * Retrieves all enrolments matching the given username from the database
     * @param username The username to be matched
     * @return A list of enrolments matching the given username
     */
    public List<Enrolment> getAllByUser(String username) {
        List<Enrolment> enrolments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM enrolments WHERE username = ?";
            PreparedStatement getAllByUnit = connection.prepareStatement(sql);
            getAllByUnit.setString(1, username);
            ResultSet rs = getAllByUnit.executeQuery();
            while (rs.next()) {
                enrolments.add(new Enrolment(rs.getString("username"),
                        rs.getString("unitCode"))
                );
            }
        } catch (SQLException ex) {
            System.err.println("enrolment get all by user error");
        }
        return enrolments;
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
