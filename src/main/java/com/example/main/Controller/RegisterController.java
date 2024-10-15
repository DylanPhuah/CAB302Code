package com.example.main.Controller;

import com.example.main.Model.ExceptionPopUp;
import com.example.main.UniPlus;
import com.example.main.Model.User;
import com.example.main.Model.DAO.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField fName;
    @FXML private TextField lName;
    @FXML private CheckBox isTeacherCheck;
    @FXML private Button registerButton;
    @FXML private Label statusLabel;

    /**
     * Performs sanity checks on user-entered register data and adds the user if valid
     * @throws IOException
     */
    @FXML
    protected void onRegisterButtonClick() throws IOException {
        User eUser = null;
        UserDAO userDAO = new UserDAO();
        // Inform the user if necessary info has not been entered, otherwise attempt to
        // register the new user
        if (username.getText() == null || username.getText().isEmpty()) {
            statusLabel.setText("please enter a username");
        }
        else if (password.getText() == null || password.getText().isEmpty()) {
            statusLabel.setText("please enter a password");
        }
        else if (fName.getText() == null || fName.getText().isEmpty()
                || lName.getText() == null || lName.getText().isEmpty()) {
            statusLabel.setText("please enter a full name");
        }
        else {
            // Attempt to retrieve the entered user from the db
            boolean dbError = false;
            try {
                eUser = userDAO.getByUser(username.getText());
            }
            catch (SQLException e) {
                dbError = true;
                ExceptionPopUp.exceptionPopUp("An error occurred with the database. " +
                                "It may be missing or corrupted.",
                        "Database missing or corrupted");
            }
            // Inform the user if the username is taken, else register the new user and
            // return to the login screen
            if (eUser != null) {
                statusLabel.setText("username is already taken");
            }
            else if (!dbError) {
                userDAO.insert(new User(username.getText(), password.getText(),
                        fName.getText(), lName.getText(), isTeacherCheck.isSelected(), 14));
                Stage stage = (Stage) registerButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/login-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 625, 353);
                stage.setScene(scene);
            }
        }
    }
}
