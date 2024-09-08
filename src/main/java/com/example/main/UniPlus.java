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
        // Connect to the database and initialise each table if it doesn't already exist
        UserDAO userDAO = new UserDAO();
        userDAO.createTable();
        EnrolmentDAO enrolmentDAO = new EnrolmentDAO();
        enrolmentDAO.createTable();
        TextbookDAO textbookDAO = new TextbookDAO();
        textbookDAO.createTable();
        // Close the database connection
        userDAO.close();
    }
}