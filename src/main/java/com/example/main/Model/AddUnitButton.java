package com.example.main.Model;

import com.example.main.Model.DAO.EnrolmentDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AddUnitButton extends Button implements CustomButton {
    private FlowPane environment;
    private boolean teacherMode;

    /**
     * Instantiates an AddUnitButton. When clicked, this will clear the workspace and set an interface for users to add units.
     * @param environment The parent button that the button itself will be located in
     * @param teacherMode Whether or not the UI environment is displaying a teacher view
     */
    public AddUnitButton(FlowPane environment, boolean teacherMode) {
        super("Add Unit");
        this.environment = environment;
        this.teacherMode = teacherMode;
        properties();
    }

    @Override
    public void fire() {
        environment.getChildren().clear();

        /* Simple text prompting the user to add a unit name */
        Label unitLabel = new Label("Add Unit Name");
        unitLabel.setTextFill(Color.WHITE);

        /* The field where users enter the unit code */
        TextField unitTextField = new TextField();
        unitTextField.setStyle("-fx-text-fill: red;");
        unitTextField.setStyle("-fx-text-inner-color: red;");
        unitLabel.setTextFill(Color.WHITE);

        /* The button to trigger adding the unit */
        Button addUnit = new Button("Add Unit");
        addUnit.setStyle(" -fx-background-color: #2e2e2e; -fx-border-color: grey;");
        addUnit.setTextFill(Color.WHITE);
        addUnit.setOnAction(event ->
        {
            EnrolmentDAO enrolmentDAO = new EnrolmentDAO();
            if(!teacherMode) //If the user is not a teacher, must check if the unit already exists,
                //otherwise students will be creating new units.
            {
                if(enrolmentDAO.getAllByUnit(unitTextField.getText()).isEmpty())
                {
                    unitTextField.setText("Error: Unit does not exist!");
                    return;
                }
            }
            Enrolment enrolment = new Enrolment(UserAccessModel.getCurrentUser().GetUsername(), unitTextField.getText());
            enrolmentDAO.insert(enrolment); //Create the enrolment, and insert it into the database.
        });


        environment.getChildren().add(unitLabel); //Add the afformentioned elements
        environment.getChildren().add(unitTextField);
        environment.getChildren().add(addUnit);
        super.fire(); //Use the parent class's fire method so it still acts like a button
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
        this.setStyle("-fx-text-fill: black;");
        this.setFont(Font.font("System Italic", 15));
        this.setPadding(new Insets(0, 0, 0, 30));
    }

}
