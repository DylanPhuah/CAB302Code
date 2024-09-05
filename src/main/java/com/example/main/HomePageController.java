package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class HomePageController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label classLabel;

    @FXML
    private ListView<String> classListView;

    private String username;
    private boolean isTeacher;

    public void initialize(String username, boolean isTeacher) {
        this.username = username;
        this.isTeacher = isTeacher;

        // Update the welcome label and class view based on the role
        welcomeLabel.setText("Welcome, " + username);
        if (isTeacher) {
            classLabel.setText("Classes you are teaching:");
            classListView.getItems().addAll("CS 101", "CS 201", "CS 301"); // Mockup data
        } else {
            classLabel.setText("Classes you are taking:");
            classListView.getItems().addAll("Math 101", "History 201", "Physics 301"); // Mockup data
        }
    }
    public static void main(String[] args) {
    }
}
