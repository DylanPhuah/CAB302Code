package com.example.PdfReader;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.transform.Scale;

public class PdfReaderController {

    private double scaleValue = 1.0; // Default zoom scale
    private final double zoomFactor = 1.1; // Zoom factor per click

    @FXML
    private Button backButton;

    @FXML
    protected void onBackButtonClick() {
        backButton.setText("Return");
    }

    @FXML
    private Label headerText;
    public Label getHeaderText(){
        return headerText;
    }
    @FXML
    private Button textToSpeech;

    @FXML
    private TextArea textArea;
    public TextArea getTextArea(){
        return textArea;
    }

    @FXML
    private Label pgNumber;

    @FXML
    private Label maxPages;

    @FXML
    private Button zoomIn;

    @FXML
    private void zoomIn() {
        scaleValue *= zoomFactor; // Increase scale
        reScale();
    }

    @FXML
    private Button zoomOut;

    private void zoomOut() {
        scaleValue /= zoomFactor; // Decrease scale
        reScale();
    }

    @FXML
    private Group scrollPaneContent;

    private void reScale() {
        Scale scale = new Scale(scaleValue, scaleValue, 0, 0);
        scrollPaneContent.getTransforms().clear(); // Clear previous transformations
        scrollPaneContent.getTransforms().add(scale); // Apply new scale
    }
}

