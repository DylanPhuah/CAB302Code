package com.example.main.Model;

import com.example.main.UniPlus;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class TextBookButton extends Button implements CustomButton {
    /** The textbook that this button will display */
    private Textbook PointedBook;
    private ImageView icon;

    /**
     * Creates a button that when clicked, will open a view of a given textbook
     * @param Book The textbook this button is associated with
     */
    public TextBookButton(Textbook Book) {
        super(Book.GetTitle()); //Set the text to be the book's title
        PointedBook = Book;

        // Set button properties
        this.setAlignment(Pos.BASELINE_LEFT);
        //this.setGraphicTextGap(10.0);
        this.setMnemonicParsing(false);
        this.setPrefSize(340, 47);
        this.setStyle("-fx-text-fill: white; -fx-background-color: #6393E7;");

        this.getStyleClass().add("textbook-button");

        this.setFont(Font.font("System Italic", 15));
        this.setPadding(new Insets(10, 10, 10, 10));

        URL iconUrl = getClass().getResource("/com/example/main/View/icons/arrowicon.png");
        if (iconUrl != null) {
            Image image = new Image(iconUrl.toString());
            icon = new ImageView(image);
            icon.setFitHeight(20); // Set desired height
            icon.setFitWidth(20);  // Set desired width
            icon.setPreserveRatio(true); // Maintain aspect ratio
            // Set the icon as the graphic for the button
            this.setGraphic(icon);
        } else {
            System.out.println("Icon not found!");
        }


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
        WindowStateUtils.WindowState windowState = WindowStateUtils.captureWindowState(stage);
        stage.setMinWidth(WindowStateUtils.minMainWidth);
        stage.setMinHeight(WindowStateUtils.minMainHeight);
        FXMLLoader fxmlLoader = new FXMLLoader(UniPlus.class.getResource("View/pdf-reader-view.fxml"));
        Scene scene = null;
        try {
            //scene = new Scene(fxmlLoader.load(), 1201, 817);
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);

        WindowStateUtils.restoreWindowState(stage, windowState);

        //stage.setFullScreen(true);

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
