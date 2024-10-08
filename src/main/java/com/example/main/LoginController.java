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

    /**
     * Performs sanity checks on user-entered login data, and logs the user in if valid
     * @throws IOException
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        UserDAO userDAO = new UserDAO();
        System.out.println(username.getText());
        User eUser = userDAO.getByUser(username.getText());
        if (username.getText() == null || username.getText().isEmpty()) {
            statusLabel.setText("please enter a username");
        }
        else if (password.getText() == null || password.getText().isEmpty()) {
            statusLabel.setText("please enter a password");
        }
        else if (eUser != null) {
            if (username.getText().equals(eUser.GetUsername()) && password.getText().equals(eUser.GetPassword())) {
                UserAcsessModel.setUser(eUser);
                System.out.println(eUser.GetPassword());
                Stage stage = (Stage) loginButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("main-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1201, 817);
                stage.setScene(scene);
            }
            else {
                statusLabel.setText("username or password is incorrect");
            }
        }
        else {
            statusLabel.setText("username or password is incorrect");
        }
    }

    /**
     * Takes the user to the registration screen
     * @throws IOException
     */
    @FXML
    protected void onRegisterButtonClick() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 506, 501);
        stage.setScene(scene);
    }
}
