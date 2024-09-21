package com.example.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.*;

public class MainController {
//    @FXML private Button backButton;
//
//    @FXML
//    protected void onBackButtonClick() throws IOException {
//        Stage stage = (Stage) backButton.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("login-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
//        stage.setScene(scene);
//    }

    @FXML
    private AnchorPane homepageAnchorPane;


    @FXML
    private ImageView contentImageView;

    @FXML
    private ImageView textbookView;

    @FXML
    private VBox UnitList;

    @FXML
    void initialize() {

        HashMap<Enrolment,List<Textbook>> info = UserAcsessModel.getInstance().getUnitTextBooks();
        Set<Enrolment> enrolmentKey = info.keySet();
        List<Enrolment> enrolments = new ArrayList<>(enrolmentKey);
        for(Enrolment enrolment : enrolments)
        {
            Button button = new Button();
            button.setStyle("@Styling.css");
            button.setText(enrolment.GetUnitCode());
            UnitList.getChildren().add(button);
        }
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/2.png")));
        contentImageView.setImage(image);

    }

    @FXML
    void onContentSwitch(ActionEvent event) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(UniPlus.class.getResource("content-view.fxml")));
        homepageAnchorPane.getChildren().removeAll();
        homepageAnchorPane.getChildren().setAll(nextAnchorPane);
    }

    @FXML
    void onTextbookSwitch(ActionEvent event) throws IOException {
        Stage stage = (Stage) textbookView.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("pdf-reader-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setScene(scene);
        }
}

