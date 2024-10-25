package com.example.main.Controller;

import com.example.main.Model.*;
import com.example.main.Model.DAO.EnrolmentDAO;
import com.example.main.Model.DAO.TaskDAO;
import com.example.main.Model.TaskManager.Task;
import com.example.main.Model.TaskManager.TaskFactory;
import com.example.main.Model.TaskManager.TaskManager;
import com.example.main.UniPlus;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class MainController {
    //    @FXML private Button backButton;
//
//    @FXML
//    protected void onBackButtonClick() throws IOException {
//        Stage stage = (Stage) backButton.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("login-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
//        stage.setScene(scene);
//    }

    private TaskManager taskManager;
    @FXML
    private VBox taskListBox;
    @FXML
    private AnchorPane homepageAnchorPane;

    @FXML
    private Button Refresh;

    @FXML
    private Button nameBanner;

    @FXML
    private VBox UnitList;

    @FXML
    private FlowPane textbookholder;

    @FXML
    private Button Logout;

    @FXML
    private ChoiceBox studentTeacherDropdown;

    @FXML
    private Label UnitBanner;

    @FXML
    void initialize() {
        User activeUser = UserAccessModel.getCurrentUser();
        taskManager = new TaskManager();

        try {
            UserAccessModel.updateUser(activeUser);
        } catch (Exception e) {
            ExceptionPopUp.exceptionPopUp("An error occurred with the database. " +
                            "It may be missing or corrupted.",
                    "Database missing or corrupted");
            Platform.exit();
        }

        Boolean shouldDisplayTeacher = UserAccessModel.getdisplayTeacher();
        nameBanner.setText(activeUser.GetUsername());
        studentTeacherDropdown.getItems().add("Student View");
        //Configure the dropdown menu to initially have student view, and set it to display that option

        if (activeUser.GetIsTeacher()) //If the user is a teacher, add an option for a teacher view
        {
            studentTeacherDropdown.getItems().add("Teacher View");
        } else {
            studentTeacherDropdown.setVisible(false);
            studentTeacherDropdown.setManaged(false);
        }


        if (shouldDisplayTeacher) {
            studentTeacherDropdown.setValue("Teacher View");
        } else {
            studentTeacherDropdown.setValue("Student View");
        }

        studentTeacherDropdown.setOnAction(event -> { //set the behaviour for the student view and teacher view button
            String selectedOption = (String) studentTeacherDropdown.getValue();
            if (selectedOption.equals("Teacher View")) {
                UserAccessModel.SetDisplayTeacher(true); //tell user access model to store this info
                Stage stage = (Stage) studentTeacherDropdown.getScene().getWindow();
                WindowStateUtils.WindowState windowState = WindowStateUtils.captureWindowState(stage);
                stage.setMinHeight(WindowStateUtils.minMainHeight);
                stage.setMinWidth(WindowStateUtils.minMainWidth);
                FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/main-view.fxml"));
                Scene scene = null;
                try {
                    //scene = new Scene(fxmlLoader.load(), 1201, 817);
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene); // reload the scene
                //stage.setFullScreen(true);
                WindowStateUtils.restoreWindowState(stage, windowState);
            } else if (selectedOption.equals("Student View")) {
                UserAccessModel.SetDisplayTeacher(false); //tell user access model to store this info
                Stage stage = (Stage) studentTeacherDropdown.getScene().getWindow();
                WindowStateUtils.WindowState windowState = WindowStateUtils.captureWindowState(stage);
                stage.setMinHeight(WindowStateUtils.minMainHeight);
                stage.setMinWidth(WindowStateUtils.minMainWidth);
                FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/main-view.fxml"));
                Scene scene = null;
                try {
                    //scene = new Scene(fxmlLoader.load(), 1201, 817);
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene); //reload the scene
                //stage.setFullScreen(true);
                WindowStateUtils.restoreWindowState(stage, windowState);
            }
        });


        /* Maps a unit to it's associated textbooks */
        HashMap<Enrolment, List<Textbook>> info = UserAccessModel.getUnitTextBooks();
        Set<Enrolment> enrolmentKey = info.keySet();
        List<Enrolment> enrolments = new ArrayList<>(enrolmentKey);
        for (Enrolment enrolment : enrolments) {
            UnitButton button = new UnitButton(enrolment, textbookholder, UnitBanner);
            UnitList.getChildren().add(button);
        }

        AddUnitButton addUnitButton = new AddUnitButton(textbookholder, activeUser.GetIsTeacher());
        UnitList.getChildren().add(addUnitButton);

        Logout.setOnAction(actionEvent -> {
            Stage stage = (Stage) studentTeacherDropdown.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/login-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 625, 353);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(scene); //reload the scene
            stage.setHeight(WindowStateUtils.minLoginHeight);
            stage.setWidth(WindowStateUtils.minLoginWidth);
            stage.setMinWidth(WindowStateUtils.minLoginWidth);
            stage.setMinHeight(WindowStateUtils.minLoginHeight);
            stage.show();
        });
        Refresh.setOnAction(actionEvent -> {
            nameBanner.setText(activeUser.GetUsername()); //We do this to get the user access model to pull the latest data.
            Stage stage = (Stage) homepageAnchorPane.getScene().getWindow();
            WindowStateUtils.WindowState windowState = WindowStateUtils.captureWindowState(stage);
            stage.setMinHeight(WindowStateUtils.minMainHeight);
            stage.setMinWidth(WindowStateUtils.minMainWidth);
            FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/main-view.fxml"));
            Scene scene = null;
            try {
                //scene = new Scene(fxmlLoader.load(), 1201, 817);
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(scene); //reload the scene
            //stage.setFullScreen(true);
            WindowStateUtils.restoreWindowState(stage, windowState);
        });


    }

    @FXML
    void accountPress() {
        // Clear existing children
        textbookholder.getChildren().clear();

        // URL for styling
        URL resource = getClass().getResource("/com/example/main/View/Styling.css");

        // Ensure taskManager and taskDAO are initialized
        if (taskManager == null) {
            taskManager = new TaskManager(); // Initialize TaskManager
        }

        TaskDAO taskDAO = new TaskDAO(); // Initialize TaskDAO

        // Load tasks from the database and add them to the TaskManager
        List<Task> loadedTasks = taskDAO.getAllTasks();
        for (Task task : loadedTasks) {
            System.out.println("Loaded task: " + task.getDescription());
            taskManager.addTask(task);
        }

        // Create an HBox to hold the input section and the task list
        HBox mainLayout = new HBox(20);
        mainLayout.setAlignment(Pos.TOP_LEFT);
        mainLayout.setPadding(new Insets(20));

        // Pass the initialized taskManager to the TaskViewController
        TaskViewController taskViewController = new TaskViewController(taskManager, taskListBox);

        // Create a VBox for the input section
        VBox inputBox = taskViewController.createInputBox(resource);

        // Create a VBox for the task list
        taskListBox = taskViewController.createTaskListBox();

        for (Task task : taskManager.getTasks() ) {
            taskViewController.onTaskAdded(task);
        }
        // Add both the input box and task list box to the main layout
        mainLayout.getChildren().addAll(inputBox, taskListBox);
        textbookholder.getChildren().add(mainLayout);


        // Optional: Set up a listener for saving new tasks
//        taskViewController.setTaskSavedCallback(task -> {
//            taskDAO.insert(task); // Save new task to the database
//            System.out.println("Saving task: " + task.getDescription());
//        });


    }
}
//
//    @FXML
//    void accountPress() {
//        // Clear existing children
//        textbookholder.getChildren().clear();
//
//        URL resource = getClass().getResource("/com/example/main/View/Styling.css");
//
//        // Create an HBox to hold the input section and the task list
//        HBox mainLayout = new HBox(20); // 20 is the spacing between elements
//        mainLayout.setAlignment(Pos.TOP_LEFT); // Align elements to the top left
//        mainLayout.setPadding(new Insets(20));
//
//        // Create a VBox for the input section
//        VBox inputBox = new VBox(10); // Spacing between elements
//        inputBox.setAlignment(Pos.TOP_LEFT); // Align elements to the top left
//        inputBox.setPadding(new Insets(0, 15, 20, 15));
//
//        // Label for the input section
//        Label inputLabel = new Label("Add Task");
//        inputLabel.setTextFill(Color.WHITE);
//        inputLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
//
//        // Text field for entering the task
//        TextField unitTextField = new TextField();
//        unitTextField.setStyle("-fx-background-color: #383838;");
//        unitTextField.setPromptText("Task description");
//
//        // Date picker for selecting the task date
//        DatePicker datePicker = new DatePicker();
//        if (resource != null) {
//            datePicker.getStylesheets().add(resource.toExternalForm());
//        }
//
//        // ComboBox for priority selection
//        ComboBox<String> priorityComboBox = new ComboBox<>();
//        priorityComboBox.getItems().addAll("Low", "High");
//        priorityComboBox.setStyle("-fx-background-color: #383838; -fx-text-fill: #ffffff;");
//        if (resource != null) {
//            priorityComboBox.getStylesheets().add(resource.toExternalForm());
//        }
//        priorityComboBox.setPromptText("Select priority");
//
//        // Button to add a task
//        Button addUnit = new Button("Add Task");
//        addUnit.setStyle("-fx-background-color: #165DDB;");
//        addUnit.setTextFill(Color.WHITE);
//        addUnit.setOnAction(event -> {
//            String taskText = unitTextField.getText();
//            LocalDate selectedDate = datePicker.getValue();
//            String selectedPriority = priorityComboBox.getValue();
//
//            if (!taskText.isEmpty() && selectedDate != null && selectedPriority != null) {
//                addTask(taskText, selectedDate, selectedPriority); // Add the task to the list
//                unitTextField.clear(); // Clear the text field after adding
//                datePicker.setValue(null); // Clear the date picker
//                priorityComboBox.setValue(null); // Clear the priority selection
//            }
//        });
//
//        // Add the label, text field, date picker, priority combo box, and button to the input box
//        inputBox.getChildren().addAll(inputLabel, unitTextField, datePicker, priorityComboBox, addUnit);
//
//        // Create a VBox for the task list
//        taskListBox = new VBox(10); // Initialize taskListBox here
//        taskListBox.setAlignment(Pos.TOP_LEFT); // Align tasks to the top left
//        taskListBox.setPadding(new Insets(0, 15, 20, 15));
//
//        // Label for the task list section
//        Label taskLabel = new Label("Current Tasks");
//        taskLabel.setTextFill(Color.WHITE);
//        taskLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
//
//        // Add task label to the task list box
//        taskListBox.getChildren().add(taskLabel);
//
//        // Add both the input box and task list box to the main layout
//        mainLayout.getChildren().addAll(inputBox, taskListBox);
//
//        // Adjusting taskListBox alignment
//        HBox.setHgrow(taskListBox, Priority.ALWAYS); // Allow taskListBox to grow
//
//        // Add the main layout to the textbook holder
//        textbookholder.getChildren().add(mainLayout);
//
//    }
//
//    private void addTask(String taskText, LocalDate date, String priority) {
//        tasks.add(new Task(taskText, date, priority)); // Add task to the list
//
//        // Create a new HBox to hold the task details
//        HBox taskBox = new HBox(10); // Spacing between elements
//        Label taskLabel = new Label(taskText + " | Due Date: " + date + " | Priority: " + priority);
//        taskLabel.setStyle("-fx-font-weight: bold;");
//        taskLabel.setTextFill(Color.WHITE);
//
//        // Button to delete the task
//        Button deleteButton = new Button("Delete");
//        deleteButton.setStyle("-fx-background-color: #165DDB;");
//        deleteButton.setTextFill(Color.WHITE);
//        deleteButton.setOnAction(event -> {
//            tasks.removeIf(task -> task.description.equals(taskText)); // Remove the task from the list
//            taskListBox.getChildren().remove(taskBox); // Remove the task display
//        });
//
//        // Add the label and delete button to the HBox
//        taskBox.getChildren().addAll(taskLabel, deleteButton);
//
//        // Add the taskBox to the task list
//        taskListBox.getChildren().add(taskBox); // Use the instance variable directly
//    }



