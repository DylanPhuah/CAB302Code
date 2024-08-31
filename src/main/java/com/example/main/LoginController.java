package com.example.main;

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

    @FXML
    protected void onLoginButtonClick() throws IOException {
        UserDAO userDAO = new UserDAO();
        User eUser = userDAO.getByUser(username.getText());

        if(eUser != null) {
            if (username.getText().equals(eUser.username) && password.getText().equals(eUser.password)) {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("main-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 720, 480);
                stage.setScene(scene);
            } else {
                statusLabel.setText("username or password is incorrect");
            }
        }
    }

    @FXML
    protected void onRegisterButtonClick() {
        UserDAO userDAO = new UserDAO();

        if (username.getText() == null || username.getText().isEmpty()) {
            statusLabel.setText("please enter a username");
        }
        else if (password.getText() == null || password.getText().isEmpty()) {
            statusLabel.setText("please enter a password");
        }
        else {
            User eUser = userDAO.getByUser(username.getText());
            if (eUser != null) {
                statusLabel.setText("username is already taken");
            }
            else {
                userDAO.insert(new User(username.getText(), password.getText(), false));
                statusLabel.setText("new user created");
            }
        }

    }
}
