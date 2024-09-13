package com.example.PdfReader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;


public class PdfReaderController {

    private double fontSize = 14; // Default font size

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
    private Button zoomIn, zoomOut;

    @FXML
    public void initialize() {
        zoomIn.setOnAction(event -> adjustFontSize(2)); // Increase font size by 2
        zoomOut.setOnAction(event -> adjustFontSize(-2)); // Decrease font size by 2
    }


    private void adjustFontSize(double delta) {
        fontSize += delta;
        if (fontSize < 4) fontSize = 4; // Minimum font size
        textArea.setStyle("-fx-font-size: " + fontSize + "pt;");
    }
}

