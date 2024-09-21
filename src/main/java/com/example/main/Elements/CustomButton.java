package com.example.main.Elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class CustomButton extends Button {

    public CustomButton(String text) {
        super(text); // Set the text (e.g., "Lachlan")

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
}
