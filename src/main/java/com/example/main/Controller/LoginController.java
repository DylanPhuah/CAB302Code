package com.example.main.Controller;

import com.example.main.Model.ExceptionPopUp;
import com.example.main.Model.WindowStateUtils;
import com.example.main.UniPlus;
import com.example.main.Model.User;
import com.example.main.Model.UserAccessModel;
import com.example.main.Model.DAO.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label statusLabel;
    @FXML private Button loginButton;

    /**
     * Performs sanity checks on user-entered login data, and logs the user in if valid
     * @throws IOException
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        User eUser = null;
        UserDAO userDAO = new UserDAO();

        // Inform the user if necessary info has not been entered, otherwise retrieve the
        // entered user from the db and log them in
        if (username.getText() == null || username.getText().isEmpty()) {
            statusLabel.setText("please enter a username");
        }
        else if (password.getText() == null || password.getText().isEmpty()) {
            statusLabel.setText("please enter a password");
        }
        else {
            // Give an error popup and stop login checking if the db encounters an error
            try {
                eUser = userDAO.getByUser(username.getText());
            }
            catch (Exception e) {
                ExceptionPopUp.exceptionPopUp("An error occurred with the database. " +
                                "It may be missing or corrupted.",
                        "Database missing or corrupted");
                return;
            }
        }
        // If a user was successfully retrieved, log them in
        if (eUser != null) {
            if (username.getText().equals(eUser.GetUsername()) && password.getText().equals(eUser.GetPassword())) {
                UserAccessModel.updateUser(eUser);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/main-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1201, 817);
                stage.setScene(scene);
                stage.setMinHeight(WindowStateUtils.minMainHeight);
                stage.setMinWidth(WindowStateUtils.minMainWidth);
            }
            else {
                statusLabel.setText("username or password is incorrect");
            }
        }
        else {
            statusLabel.setText("username or password is incorrect");        }
    }

    /**
     * Takes the user to the registration screen
     * @throws IOException
     */
    @FXML
    protected void onRegisterButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 506, 501);
        stage.setScene(scene);
    }
}
