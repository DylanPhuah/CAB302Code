package com.example.main.Model.DAO;



import com.example.main.Model.TaskManager.Task;
import com.example.main.Model.UserAccessModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** A database access object for the tasks table */
public class TaskDAO {
    private final Connection connection;

    /** Instantiates a database access object for the tasks table */
    public TaskDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /** Creates the tasks table in the database if it does not already exist */
    public void createTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute("CREATE TABLE IF NOT EXISTS tasks ("
                    + "description VARCHAR NOT NULL, "
                    + "date DATE NOT NULL, "
                    + "priority VARCHAR NOT NULL,"
                    + "PRIMARY KEY (username, description))");
        } catch (SQLException ex) {
            System.err.println("Task table creation error: " + ex.getMessage());
        }
    }

    /**
     * Inserts a given task into the database
     * @param task The task to be inserted
     */
    public void insert(Task task) {
        try {
            PreparedStatement insertTask = connection.prepareStatement(
                    "INSERT INTO tasks (description, date, priority) VALUES (?, ?, ?)");


            insertTask.setString(1, task.getDescription());
            insertTask.setDate(2, Date.valueOf(task.getDate()));
            insertTask.setString(3, task.getPriority());
            insertTask.executeUpdate(); // Use executeUpdate() for INSERT
        } catch (SQLException ex) {
            System.err.println("Task insertion error: " + ex.getMessage());
        }
    }

    /**
     * Retrieves all tasks with a specific priority from the database
     * @param priority The priority to be matched
     * @return A list of all tasks with the given priority
     */
    public List<Task> getAllByPriority(String priority) {
        List<Task> tasks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tasks WHERE priority = ?";
            PreparedStatement getAllByPriority = connection.prepareStatement(sql);
            getAllByPriority.setString(1, priority);
            ResultSet rs = getAllByPriority.executeQuery();
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getString("description"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("priority")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Task get all by priority error: " + ex.getMessage());
        }
        return tasks;
    }

    /**
     * Deletes a task from the database
     * @param task The task to be deleted
     */
    public void delete(Task task) {
        String deleteQuery = "DELETE FROM tasks WHERE description = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, task.getDescription());
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.err.println("Task delete failure: " + e.getMessage());
        }
    }

    /**
     * Retrieves all tasks from the database
     * @return A list of all tasks
     */
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tasks";
            PreparedStatement getAllTasks = connection.prepareStatement(sql);
            ResultSet rs = getAllTasks.executeQuery();
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getString("description"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("priority")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Task get all tasks error: " + ex.getMessage());
        }
        return tasks;
    }

    /** Closes the database connection */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Task connection close error: " + ex.getMessage());
        }
    }
}
