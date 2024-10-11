package com.example.main;

import com.example.main.Model.DAO.EnrolmentDAO;
import com.example.main.Model.DAO.TextbookDAO;
import com.example.main.Model.DAO.UserDAO;
import com.example.main.Model.UserAccessModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UniPlus extends Application {
    // Initialise parameters for the login window
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 625, 353);
        stage.setTitle("UniPlus");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void stop() {
        if (UserAccessModel.getCurrentUser() != null) {
            UserDAO userDAO = new UserDAO();
            userDAO.changeTextPreference(UserAccessModel.getCurrentUser().GetUsername(),
                    UserAccessModel.getCurrentUser().GetTextPreference());
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