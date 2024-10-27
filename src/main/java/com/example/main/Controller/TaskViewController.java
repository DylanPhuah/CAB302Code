package com.example.main.Controller;

import com.example.main.Model.DAO.TaskDAO;
import com.example.main.Model.TaskManager.*;
import com.example.main.Model.UserAccessModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public class TaskViewController implements TaskObserver {

    private TaskManager taskManager;
    private VBox taskListBox;
    private TaskSavedCallback taskSavedCallback;


    public TaskViewController(TaskManager taskManager, VBox taskListBox) {
        this.taskManager = taskManager;
        this.taskListBox = taskListBox;
        taskManager.addObserver(this);

    }


    public VBox createInputBox(URL resource) {
        VBox inputBox = new VBox(10);
        inputBox.setAlignment(Pos.TOP_LEFT);
        inputBox.setPadding(new Insets(0, 15, 20, 15));

        Label inputLabel = new Label("Add Task");
        inputLabel.setTextFill(Color.WHITE);
        inputLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField unitTextField = new TextField();
        unitTextField.setStyle("-fx-background-color: #383838;");
        unitTextField.setPromptText("Task description");

        DatePicker datePicker = new DatePicker();
        if (resource != null) {
            datePicker.getStylesheets().add(resource.toExternalForm());
        }

        ComboBox<String> priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().addAll("Low", "Medium", "High");
        priorityComboBox.setStyle("-fx-background-color: #383838; -fx-text-fill: #ffffff;");
        if (resource != null) {
            priorityComboBox.getStylesheets().add(resource.toExternalForm());
        }
        priorityComboBox.setPromptText("Select priority");

        Button addUnit = new Button("Add Task");
        addUnit.setStyle("-fx-background-color: #165DDB;");
        addUnit.setTextFill(Color.WHITE);
        addUnit.setOnAction(event -> {
            String taskText = unitTextField.getText();
            LocalDate selectedDate = datePicker.getValue();
            String selectedPriority = priorityComboBox.getValue();

            if (!taskText.isEmpty() && selectedDate != null && selectedPriority != null) {
                Task task = TaskFactory.createTask(UserAccessModel.getCurrentUser().GetUsername(), taskText, selectedDate, selectedPriority);
                taskManager.addTask(task);
                unitTextField.clear();
                datePicker.setValue(null);
                priorityComboBox.setValue(null);
                TaskDAO taskDAO = new TaskDAO();
                taskDAO.insert(task);
            }
        });

        inputBox.getChildren().addAll(inputLabel, unitTextField, datePicker, priorityComboBox, addUnit);
        return inputBox;
    }

    public VBox createTaskListBox() {
        taskListBox = new VBox(10);
        taskListBox.setAlignment(Pos.TOP_LEFT);
        taskListBox.setPadding(new Insets(0, 15, 20, 15));

        Label taskLabel = new Label("Current Tasks");
        taskLabel.setTextFill(Color.WHITE);
        taskLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        taskListBox.getChildren().add(taskLabel);
        return taskListBox;
    }

    @Override
    public void onTaskAdded(Task task) {
        HBox taskBox = new HBox(10);
        Label taskLabel = new Label(task.getDescription() + " | Due Date: " + task.getDate() + " | Priority: " + task.getPriority());
        taskLabel.setStyle("-fx-font-weight: bold;");
        taskLabel.setTextFill(Color.WHITE);

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #165DDB;");
        deleteButton.setTextFill(Color.WHITE);
        deleteButton.setOnAction(event -> {
            taskManager.removeTask(task.getDescription());
            taskListBox.getChildren().remove(taskBox);
            TaskDAO taskDAO = new TaskDAO();
            taskDAO.delete(task);
        });

        taskBox.getChildren().addAll(taskLabel, deleteButton);
        taskListBox.getChildren().add(taskBox);
    }

    @Override
    public void onTaskRemoved(Task task) {
        // Handle task removal if needed, or you can manage this in the addTask method
        taskListBox.getChildren().removeIf(node -> {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                Label label = (Label) hBox.getChildren().get(0);
                return label.getText().contains(task.getDescription());
            }
            return false;
        });
    }
    public void setTaskSavedCallback(TaskSavedCallback callback) {
        this.taskSavedCallback = callback;
    }

    // Call this method when saving a new task
    private void saveTask(Task task) {
        if (taskSavedCallback != null) {
            taskSavedCallback.onTaskSaved(task); // Trigger the callback
        }
    }

}
