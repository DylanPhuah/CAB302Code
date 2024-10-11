package com.example.main.Controller;
import com.example.main.Model.TextToSpeech;
import com.example.main.UniPlus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;
import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;



public class ContentController {

    private String result;

    @FXML
    private AnchorPane contentAnchorPane;

    @FXML
    private ImageView contentImageView;

    @FXML
    private TextArea contentTextArea;

    @FXML
    void onBackSwitch() throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(Objects.requireNonNull(UniPlus.class.getResource("main-view.fxml")));
        contentAnchorPane.getChildren().removeAll();
        contentAnchorPane.getChildren().setAll(nextAnchorPane);
    }

    @FXML
    void onTextToSpeech() {
        TextToSpeech text = new TextToSpeech(result);
    }
}

