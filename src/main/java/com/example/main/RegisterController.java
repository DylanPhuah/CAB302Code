package com.example.main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class RegisterController {
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField fName;
    @FXML private TextField lName;
    @FXML private CheckBox isTeacherCheck;
    @FXML private Button registerButton;
    @FXML private Label statusLabel;

    @FXML
    protected void onRegisterButtonClick() throws IOException {
        UserDAO userDAO = new UserDAO();

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
            User eUser = userDAO.getByUser(username.getText());
            if (eUser != null) {
                statusLabel.setText("username is already taken");
            }
            else {
                userDAO.insert(new User(username.getText(), password.getText(),
                        fName.getText(), lName.getText(), isTeacherCheck.isSelected()));
                Stage stage = (Stage) registerButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("login-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 720, 480);
                stage.setScene(scene);
            }
        }
    }
}
