package com.example.main.Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitButton extends Button implements CustomButton {
    private List<Textbook> books = new ArrayList<Textbook>();
    private List<TextBookButton> bookButtons = new ArrayList<TextBookButton>();
    private FlowPane TextBookEnvironment;
    private Label Banner;
    private String UnitCode;


    public UnitButton(Enrolment enrolment, FlowPane BookEnvironment, Label banner) {
        super(enrolment.GetUnitCode()); //Set the text
        UnitCode = enrolment.GetUnitCode();
        Banner = banner;
        banner.setText(enrolment.GetUnitCode());
        TextBookEnvironment = BookEnvironment;
        HashMap<Enrolment,List<Textbook>> EnrolmentBooks = UserAccessModel.getUnitTextBooks();
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
        if(UserAccessModel.getdisplayTeacher())
        {
            Button addTextbook = new Button("Add textbook");
            addTextbook.setOnAction(event ->
            {
                PdfPathPopUp pdfPathPopUp = new PdfPathPopUp();
                pdfPathPopUp.displayPopup(UnitCode);

            });
            TextBookEnvironment.getChildren().add(addTextbook);
        }
        for(TextBookButton button : bookButtons)
        {
            TextBookEnvironment.getChildren().add(button);
        }
        // Optionally, call the parent class's fire() method to maintain the original behavior
        super.fire();
    }

    @Override
    public void styleCSS(String css) {

    }

    @Override
    public void properties() {
        int a;
    }
}
