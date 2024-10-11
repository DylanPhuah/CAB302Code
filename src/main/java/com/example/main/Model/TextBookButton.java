package com.example.main.Model;

import com.example.main.UniPlus;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class TextBookButton extends Button implements CustomButton {
    private Textbook PointedBook;
    private UserAccessModel info;


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
        UserAccessModel.RequestTextBookView(PointedBook);
        Stage stage = (Stage) this.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/pdf-reader-view.fxml"));
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

    @Override
    public void styleCSS(String css) {
        // imp
    }

    @Override
    public void properties() {
        // imp
    }
}
