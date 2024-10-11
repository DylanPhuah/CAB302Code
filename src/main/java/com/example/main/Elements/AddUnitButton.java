package com.example.main.Elements;

import com.example.main.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddUnitButton extends Button implements CustomButton {
    private FlowPane environment;
    private AnchorPane homepageAnchorPane;

    public AddUnitButton(FlowPane environment, AnchorPane homepageAnchorPane) {
        super("Add Unit");
        this.environment = environment;
        this.homepageAnchorPane = homepageAnchorPane;
        properties();
    }

    @Override
    public void fire() {
        environment.getChildren().clear();

        Label unitLabel = new Label("Add Unit Name");
        unitLabel.setTextFill(Color.WHITE);

        TextField unitTextField = new TextField();
        unitLabel.setTextFill(Color.WHITE);

        Button addUnit = new Button("Add Unit");
        addUnit.setTextFill(Color.WHITE);
        addUnit.setOnAction(event ->
        {
            EnrolmentDAO enrolmentDAO = new EnrolmentDAO();
            Enrolment enrolment = new Enrolment(UserAcsessModel.getCurrentUser().GetUsername(), unitTextField.getText());
            enrolmentDAO.insert(enrolment);
        });
        environment.getChildren().add(unitLabel);
        environment.getChildren().add(unitTextField);
        environment.getChildren().add(addUnit);
        // Optionally, call the parent class's fire() method to maintain the original behavior
        super.fire();
    }

    @Override
    public void styleCSS(String css) {
    // Optionally apply the stylesheet
        this.getStylesheets().add("src/main/resources/com/example/main/Styling.css");
       // C:\Users\Lachlan\IdeaProjects\CAB302Code\src\main\resources\com\example\main\Styling.css
    }

    @Override
    public void properties() {
        // Set button properties
        this.setAlignment(Pos.BASELINE_LEFT);
        this.setGraphicTextGap(10.0);
        this.setMnemonicParsing(false);
        this.setPrefSize(340, 47);
        this.setStyle("-fx-text-fill: white;");
        this.setFont(Font.font("System Italic", 15));
        this.setPadding(new Insets(0, 0, 0, 30));
    }

}
