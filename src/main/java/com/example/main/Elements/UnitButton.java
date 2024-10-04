package com.example.main.Elements;

import com.example.main.Enrolment;
import com.example.main.Textbook;
import com.example.main.UserAcsessModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitButton extends Button {
    private List<Textbook> books = new ArrayList<Textbook>();
    private UserAcsessModel info;
    private List<TextBookButton> bookButtons = new ArrayList<TextBookButton>();
    private FlowPane TextBookEnvironment;
    private Label Banner;


    public UnitButton(Enrolment enrolment, FlowPane BookEnvironment, Label banner) {
        super(enrolment.GetUnitCode()); //Set the text
        Banner = banner;
        banner.setText(enrolment.GetUnitCode());
        TextBookEnvironment = BookEnvironment;
        info = UserAcsessModel.getInstance();
        HashMap<Enrolment,List<Textbook>> EnrolmentBooks = UserAcsessModel.getInstance().getUnitTextBooks();
        books = EnrolmentBooks.get(enrolment);
        for(Textbook book: books)
        {
            TextBookButton bookButton = new TextBookButton(book);
            bookButtons.add(bookButton);
        }
        // Set button properties
        this.setAlignment(Pos.BASELINE_LEFT);
        this.setGraphicTextGap(10.0);
        this.setMnemonicParsing(false);
        this.setPrefSize(340, 47);
        this.setStyle("-fx-text-fill: white;");
        this.setFont(Font.font("System Italic", 15));
        this.setPadding(new Insets(0, 0, 0, 30));
        this.setStyle("-fx-background-color: #1e1e1e;");
        this.setStyle("-fx-background-radius: 0em;");

        // Optionally apply the stylesheet
        //this.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
    }
    @Override
    public void fire() {
        TextBookEnvironment.getChildren().clear();
        for(TextBookButton button : bookButtons)
        {
            TextBookEnvironment.getChildren().add(button);
        }
        // Optionally, call the parent class's fire() method to maintain the original behavior
        super.fire();
    }
}
