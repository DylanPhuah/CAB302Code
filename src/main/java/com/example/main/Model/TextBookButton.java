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
    /** The textbook that this button will display */
    private Textbook PointedBook;

    /**
     * Creates a button that when clicked, will open a view of a given textbook
     * @param Book The textbook this button is associated with
     */
    public TextBookButton(Textbook Book) {
        super(Book.GetTitle()); //Set the text to be the book's title
        PointedBook = Book;

        // Set button properties
        this.setAlignment(Pos.BASELINE_LEFT);
        this.setGraphicTextGap(10.0);
        this.setMnemonicParsing(false);
        this.setPrefSize(340, 47);
        this.setStyle("-fx-text-fill: white; -fx-border-color: grey;");
        this.setFont(Font.font("System Italic", 15));
        this.setPadding(new Insets(0, 0, 0, 30));


    }
    /**
     * Fire overide that will load the PDF reader scene for the button's textbook
     */
    @Override
    public void fire() {
        //Store a reference to the textbook to be displayed so that the textbook viewer can call it upon load
        UserAccessModel.RequestTextBookView(PointedBook);

        //Initiate loading of the textbook viewer scene
        Stage stage = (Stage) this.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/pdf-reader-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1000, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.setFullScreen(true);

        super.fire(); //Call the parent fire method so the button will still behave as expected
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
