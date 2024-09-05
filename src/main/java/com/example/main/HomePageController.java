package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomePageController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private TilePane classTilePane;

    private String username;
    private boolean isTeacher;

    public void initialize(String username, boolean isTeacher) {
        this.username = username;
        this.isTeacher = isTeacher;

        // Set welcome label
        welcomeLabel.setText("Hello, " + username);

        // Add classes dynamically
        List<String> classes = isTeacher ? List.of("Math 101", "Math 201", "Math 301") : List.of("Math 101", "History 201", "Physics 301");
        for (String className : classes) {
            addClassButton(className);
        }

        // Set logout button action (optional)
        logoutButton.setOnAction(event -> {
            // Handle logout action
            System.out.println("Logging out...");
        });
    }

    private void addClassButton(String className) {
        // Create a button for each class
        Button classButton = new Button(className);
        classButton.setMinSize(200, 100);
        classButton.setStyle("-fx-font-size: 18;");

        // Set action for class button
        classButton.setOnAction(event -> {
            showClassOptions(className);
        });

        // Add the button to the tile pane
        classTilePane.getChildren().add(classButton);
    }

    private void showClassOptions(String className) {
        // Create a list of options based on the role
        List<String> options = new ArrayList<>();
        if (isTeacher) {
            options.add("Student List");
            options.add("Assign Readings");
            options.add("Leave Class");
        } else {
            options.add("Student List");
            options.add("Assigned Readings");
        }

        // Create a dialog for selecting an option
        ChoiceDialog<String> dialog = new ChoiceDialog<>(options.get(0), options);
        dialog.setTitle(className + " Options");
        dialog.setHeaderText("Select an option for " + className);

        // Show the dialog and capture the result
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(option -> handleSelectedOption(className, option));
    }

    private void handleSelectedOption(String className, String option) {
        // Handle the selected option (e.g., Student List, Assign/Assigned Readings, Leave Class)
        System.out.println("Selected option for " + className + ": " + option);

        // Optionally, show a message or perform action based on the selection
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Option Selected");
        alert.setHeaderText(null);
        alert.setContentText("You selected: " + option + " for " + className);
        alert.showAndWait();
    }
}

