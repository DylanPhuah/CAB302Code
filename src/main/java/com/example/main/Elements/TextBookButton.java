package com.example.main.Elements;

import com.example.main.Enrolment;
import com.example.main.Textbook;
import com.example.main.UniPlus;
import com.example.main.UserAcsessModel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextBookButton extends Button {
    private Textbook PointedBook;
    private UserAcsessModel info;


    public TextBookButton(Textbook Book) {
        super(Book.GetTitle()); //Set the text
        PointedBook = Book;

        // Set button properties
        this.setAlignment(Pos.BASELINE_LEFT);
        this.setGraphicTextGap(10.0);
        this.setMnemonicParsing(false);
        this.setPrefSize(340, 47);
        this.setStyle("-fx-text-fill: white;");
        this.setFont(Font.font("System Italic", 15));
        this.setPadding(new Insets(0, 0, 0, 30));

        // Optionally apply the stylesheet
        //this.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
    }
    @Override
    public void fire() {
        UserAcsessModel.RequestTextBookView(PointedBook);
        Stage stage = (Stage) this.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("pdf-reader-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1000, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        // Define custom behavior directly

        // Optionally, call the parent class's fire() method to maintain the original behavior
        super.fire();
    }
}
