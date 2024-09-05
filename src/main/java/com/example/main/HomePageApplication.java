package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.main.HomePageController;

public class HomePageApplication extends Application {

    private String username = "person1";
    private boolean isTeacher = true; // Set to true for teacher, false for student

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home-page.fxml"));
        Parent root = loader.load();

        // Access the controller and pass data
        HomePageController controller = loader.getController();
        controller.initialize(username, isTeacher);

        // Set up the scene and stage
        primaryStage.setTitle("Home Page");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
}
