package com.example.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.List;


public class PdfReaderController {

    private String result;
    private TextToSpeech tts;

    private int fontSize = 14; // Default font size

    @FXML
    private Button backButton;

    @FXML
    protected void onBackButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1201, 817);
        stage.setScene(scene);
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

    /** Initialises the textbook view, setting up the UI elements
     * and retrieving the textbook data. Currently sets up the zoom
     * buttons and pulls information from the database to be displayed
     * on the header and text area.
     */
    @FXML
    public void initialize() {
        zoomIn.setOnAction(event -> adjustFontSize(2)); // Increase font size by 2
        zoomOut.setOnAction(event -> adjustFontSize(-2)); // Decrease font size by 2

        UserDAO userDAO = new UserDAO();
        fontSize = UserAcsessModel.getCurrentUser().GetTextPreference();
        textArea.setStyle("-fx-font-size: " + fontSize + "pt;");

        TextbookDAO textbookDAO = new TextbookDAO();

        // Retrieves textbooks from database
        Textbook display = UserAcsessModel.getRequested();

        if (display != null) {
            // Sets Header and text of the stage
            getHeaderText().setText(display.GetTitle());
            getTextArea().setText(display.GetText());
            result = display.GetText();
        } else {
            // Handle case where no textbooks are found
            getHeaderText().setText("No textbook found");
            getTextArea().setText("No content available.");
        }
    }

    /**Adjust font size of the text inside the text area
     * and the scrollbar.
     * @param delta integer used to lower or increase text size
     */
    @FXML
    private void adjustFontSize(int delta) {
        fontSize += delta;
        if (fontSize < 4) fontSize = 4; // Minimum font size
        textArea.setStyle("-fx-font-size: " + fontSize + "pt;");
        UserAcsessModel.setTextPreference(fontSize);
    }

    @FXML
    void onTextToSpeech() {
        TextToSpeech tts = null;
        if (tts != null) {
            tts.stopSpeaking();
        }
        tts = new TextToSpeech(result);
    }
}

