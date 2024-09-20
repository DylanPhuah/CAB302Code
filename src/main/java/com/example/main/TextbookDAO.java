package com.example.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** A database access object for the textbooks table */
public class TextbookDAO {
    private final Connection connection;

    /** Instantiates a database access object for the textbooks table */
    public TextbookDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /** Creates the textbooks table in the database if it does not already exist */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute("CREATE TABLE IF NOT EXISTS textbooks ("
                                  + "title VARCHAR, "
                                  + "unitCode VARCHAR, "
                                  + "text TEXT, "
                                  + "PRIMARY KEY (title, unitCode))" );
        } catch (SQLException ex) {
            System.err.println("textbook table creation error");
        }
    }
    public void deleteTextbook(Textbook textbook)
    {
        String deleteQuery = "DELETE FROM textbooks WHERE title = ? AND unitCode = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, textbook.GetTitle());  // The ID or condition for deletion
            preparedStatement.setString(2, textbook.GetUnitCode());  // The ID or condition for deletion
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.err.println("User delete failure");
        }
    }

    /**
     * Inserts a given textbook into the database
     * @param textbook The textbook to be inserted
     */
    public void insert(Textbook textbook) {
        try {
            PreparedStatement insertTextbook = connection.prepareStatement(
                    "INSERT OR IGNORE INTO textbooks (title, unitCode, text) "
                      + "VALUES (?, ?, ?)" );
            insertTextbook.setString(1, textbook.GetTitle());
            insertTextbook.setString(2, textbook.GetUnitCode());
            insertTextbook.setString(3, textbook.GetText());
            insertTextbook.execute();
        } catch (SQLException ex) {
            System.err.println("textbook insertion error");
        }
    }

    /**
     * Retrieves a list of textbooks matching the given unit code
     * @param unitCode The unit code to match
     * @return A list of textbooks with the given unit code
     */
    public List<Textbook> getAllByUnit(String unitCode) {
        List<Textbook> textbooks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM textbooks WHERE unitCODE = ?";
            PreparedStatement getAllByUnit = connection.prepareStatement(sql);
            getAllByUnit.setString(1, unitCode);
            ResultSet rs = getAllByUnit.executeQuery();
            while (rs.next()) {
                textbooks.add(new Textbook(rs.getString("title"),
                        rs.getString("unitCode"),
                        rs.getString("text"))
                );
            }
        } catch (SQLException ex) {
            System.err.println("textbook get all by unit error");
        }
        return textbooks;
    }

    /** Closes the database connection */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("textbook connection close error");
        }
    }
}
