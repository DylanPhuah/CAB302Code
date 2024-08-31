package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UniPlus extends Application {
    // Initialise parameters for the login window
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("Login Screen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the login window
        launch();
        // Connect to the database and create a new table of users if none exists
        UserDAO userDAO = new UserDAO();
        userDAO.createTable();
        // Close the database
        userDAO.close();
    }
}