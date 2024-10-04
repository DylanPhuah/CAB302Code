package com.example.main;

import com.example.main.Elements.CustomButton;
import com.example.main.Elements.UnitButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    private Button nameBanner;

    @FXML
    private VBox UnitList;

    @FXML
    private FlowPane textbookholder;

    @FXML
    private Label UnitBanner;

    @FXML
    void initialize() {
        nameBanner.setText(UserAcsessModel.getCurrentUser().GetUsername());

        HashMap<Enrolment,List<Textbook>> info = UserAcsessModel.getUnitTextBooks();
        Set<Enrolment> enrolmentKey = info.keySet();
        List<Enrolment> enrolments = new ArrayList<>(enrolmentKey);
        for(Enrolment enrolment : enrolments)
        {
            UnitButton button = new UnitButton(enrolment,textbookholder,UnitBanner);
            UnitList.getChildren().add(button);
        }


    }

    @FXML
    void onContentSwitch(ActionEvent event) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(UniPlus.class.getResource("content-view.fxml")));
        homepageAnchorPane.getChildren().removeAll();
        homepageAnchorPane.getChildren().setAll(nextAnchorPane);
    }

//    @FXML
//    void onTextbookSwitch(ActionEvent event) throws IOException {
//        Stage stage = (Stage) textbookView.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("pdf-reader-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
//        stage.setScene(scene);
//    }
}

