package com.example.main.Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitButton extends Button implements CustomButton {
    private List<Textbook> books = new ArrayList<Textbook>();
    private List<TextBookButton> bookButtons = new ArrayList<TextBookButton>();
    private FlowPane TextBookEnvironment;
    private Label Banner;
    private String UnitCode;
    private ImageView icon;


    public UnitButton(Enrolment enrolment, FlowPane BookEnvironment, Label banner) {
        super(enrolment.GetUnitCode()); //Set the text
        UnitCode = enrolment.GetUnitCode();
        Banner = banner;
        banner.setText(enrolment.GetUnitCode());
        TextBookEnvironment = BookEnvironment;

        URL iconUrl = getClass().getResource("/com/example/main/View/icons/bookicon.png");
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

        HashMap<Enrolment,List<Textbook>> EnrolmentBooks = UserAccessModel.getUnitTextBooks();
        books = EnrolmentBooks.get(enrolment);
        for(Textbook book: books)
        {
            TextBookButton bookButton = new TextBookButton(book);
            bookButtons.add(bookButton);
        }
        properties();
        styleCSS("/com/example/main/View/Styling.css");
    }
    @Override
    public void fire() {
        TextBookEnvironment.getChildren().clear();
        if(UserAccessModel.getdisplayTeacher())
        {
            Button addTextbook = new Button("Add textbook");
            addTextbook.setStyle("-fx-text-fill: white; -fx-background-color: #2e2e2e; -fx-border-color: grey;");
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
        // Optionally apply the stylesheet
        URL resource = getClass().getResource(css);
        if (resource != null) {
            this.getStylesheets().add(resource.toExternalForm());
        } else {
            System.out.println("Stylesheet not found!");
        }
    }

    @Override
    public void properties() {
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
    }
}
