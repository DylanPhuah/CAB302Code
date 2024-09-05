package com.example.jfxpdfreader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.IOException;


public class HelloController {
    @FXML
    private Button backButton;

    PdfReader pdfReader =  new PdfReader();

    @FXML
    protected void onBackButtonClick() throws IOException {
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

