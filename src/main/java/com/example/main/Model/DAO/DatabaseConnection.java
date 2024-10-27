package com.example.main.Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/** Creates a connection to the sqlite database using JDBC */
public class DatabaseConnection {
    private static Connection instance = null;

    /** Instantiates a connection reference*/
    private DatabaseConnection() {
        String url = "jdbc:sqlite:UniPlus.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }
    /** Returns the instance when called. Will create one if it does not already exist*/
    public static Connection getInstance() {
        if (instance == null) {
            new DatabaseConnection();
        }
        return instance;
    }
}