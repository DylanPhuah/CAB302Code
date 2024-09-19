package com.example.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrolmentDAO {
    private final Connection connection;

    public EnrolmentDAO() {
        connection = DatabaseConnection.getInstance();
    }

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

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("user connection close error");
        }
    }
}
