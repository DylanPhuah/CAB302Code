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
    private Button Refresh;

    @FXML
    private Button nameBanner;

    @FXML
    private VBox UnitList;

    @FXML
    private FlowPane textbookholder;

    @FXML
    private Button Logout;

    @FXML
    private ChoiceBox studentTeacherDropdown;

    @FXML
    private Label UnitBanner;

    @FXML
    void initialize() {
        User activeUser = UserAcsessModel.getCurrentUser();
        UserAcsessModel.setUser(activeUser);




        Boolean shouldDisplayTeacher = UserAcsessModel.getdisplayTeacher();


        studentTeacherDropdown.getItems().add("Student View"); //Configure the dropdown menu to initially have student view, and set it to display that option

        if(activeUser.GetIsTeacher()) //If the user is a teacher, add an option for a teacher view
        {

            studentTeacherDropdown.getItems().add("Teacher View");
        }
        else
        {

        }


        if(shouldDisplayTeacher)
        {
            studentTeacherDropdown.setValue("Teacher View");
        }
        else
        {
            studentTeacherDropdown.setValue("Student View");
        }
        studentTeacherDropdown.setOnAction(event -> { //set the behaviour for the student view and teacher view button
            String selectedOption = (String) studentTeacherDropdown.getValue();
            if(selectedOption.equals("Teacher View"))
            {
                UserAcsessModel.SetDisplayTeacher(true); //tell user access model to store this info
                Stage stage = (Stage) studentTeacherDropdown.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("main-view.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 1000, 800);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene); // reload the scene
            } else if (selectedOption.equals("Student View"))
            {
                UserAcsessModel.SetDisplayTeacher(false); //tell user access model to store this info
                Stage stage = (Stage) studentTeacherDropdown.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("main-view.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 1000, 800);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene); //reload the scene
            }
        });



        HashMap<Enrolment,List<Textbook>> info = UserAcsessModel.getUnitTextBooks();
        Set<Enrolment> enrolmentKey = info.keySet();
        List<Enrolment> enrolments = new ArrayList<>(enrolmentKey);
        for(Enrolment enrolment : enrolments)
        {
            UnitButton button = new UnitButton(enrolment,textbookholder,UnitBanner);
            UnitList.getChildren().add(button);
        }

        Logout.setOnAction(actionEvent -> {
            Stage stage = (Stage) studentTeacherDropdown.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("login-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 625, 353);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(scene); //reload the scene
        });
        Refresh.setOnAction(actionEvent -> {
            nameBanner.setText(activeUser.GetUsername()); //We do this to get the user acsess model to pull the latest data.
            Stage stage = (Stage) homepageAnchorPane.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("main-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1000, 800);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(scene); //reload the scene
        });


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

