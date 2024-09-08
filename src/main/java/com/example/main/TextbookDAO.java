package com.example.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TextbookDAO {
    private final Connection connection;

    public TextbookDAO() {
        connection = DatabaseConnection.getInstance();
    }

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

    public void insert(Textbook textbook) {
        try {
            PreparedStatement insertTextbook = connection.prepareStatement(
                    "INSERT OR IGNORE INTO textbooks (title, unitCode, text) "
                      + "VALUES (?, ?, ?)" );
            insertTextbook.setString(1, textbook.title);
            insertTextbook.setString(2, textbook.unitCode);
            insertTextbook.setString(3, textbook.text);
            insertTextbook.execute();
        } catch (SQLException ex) {
            System.err.println("textbook insertion error");
        }
    }

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

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("textbook connection close error");
        }
    }
}
