package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UniPlus extends Application {
    // Initialise parameters for the login window
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 625, 353);
        stage.setTitle("UniPlus");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() {
        if (UserAcsessModel.getCurrentUser() != null) {
            UserDAO userDAO = new UserDAO();
            userDAO.changeTextPreference(UserAcsessModel.getCurrentUser().GetUsername(),
                    UserAcsessModel.getCurrentUser().GetTextPreference());
            userDAO.close();
        }
    }

    public static void main(String[] args) throws IOException {
        // Connect to the database by each table and initialise if any do not already exist
        UserDAO userDAO = new UserDAO();
        userDAO.createTable();
        EnrolmentDAO enrolmentDAO = new EnrolmentDAO();
        enrolmentDAO.createTable();
        TextbookDAO textbookDAO = new TextbookDAO();
        textbookDAO.createTable();

        // Stores the name of pdf file and its content into the database -- to be polished later
        //PdfReader pdfReader = new PdfReader();
        //pdfReader.readPdf("src/pdFile/test.pdf");

        // Launch the login window
        launch();
    }
}