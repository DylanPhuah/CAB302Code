package com.example.PdfReader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PdfReaderController {
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

}

