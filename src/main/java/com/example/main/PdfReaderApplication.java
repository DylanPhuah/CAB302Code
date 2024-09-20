package com.example.main;
/*
--------------------------------------------Stage for Textbook Reader Page ----------------------------------------------------------------
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PdfReaderApplication extends Application {

    private final TextbookDAO textbookDAO = new TextbookDAO();
    PdfReader pdfReader = new PdfReader();

    @Override
    public void start(Stage stage) throws IOException {
        // Load FXML file and set the stage
        FXMLLoader fxmlLoader = new FXMLLoader(PdfReaderApplication.class.getResource("pdf-reader-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Textbook Reader");
        stage.setScene(scene);
        stage.show();

        PdfReaderController controller = fxmlLoader.getController();

        // Stores the name of pdf file and its content into the database -- to be polished later
        pdfReader.readPdf("src/pdFile/test.pdf");

        // Placeholder unitCode used to select specific textbook
        String unitCode = "EGB101";

        // Retrieves textbooks from database
        List<Textbook> textbooks = textbookDAO.getAllByUnit(unitCode);

        if (!textbooks.isEmpty()) {
            Textbook textbook = textbooks.getFirst(); // Assuming you want the first result
            // Sets Header and text of the stage
            controller.getHeaderText().setText(textbook.GetTitle());
            controller.getTextArea().setText(textbook.GetText());
        } else {
            // Handle case where no textbooks are found
            controller.getHeaderText().setText("No textbook found");
            controller.getTextArea().setText("No content available.");
        }

        // Close the DAO connection
        textbookDAO.close();

    }

    public static void main(String[] args) {
        launch();
    }
}
