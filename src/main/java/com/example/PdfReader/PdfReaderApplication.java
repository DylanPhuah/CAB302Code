package com.example.PdfReader;
/*
--------------------------------------------Stage for Textbook Reader Page ----------------------------------------------------------------
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class PdfReaderApplication extends Application {

    PdfReader pdfReader = new PdfReader();

    @Override
    public void start(Stage stage) throws IOException {
        // Load FXML file and set the stage
        FXMLLoader fxmlLoader = new FXMLLoader(PdfReaderApplication.class.getResource("pdf-reader-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1415, 1000);
        stage.setTitle("Textbook Reader");
        stage.setScene(scene);
        stage.show();

        PdfReaderController controller = fxmlLoader.getController();

        // Stores the name of pdf file and its content into an array
        String[] pdfContent = pdfReader.readPdf("C:/Users/Juan/IdeaProjects/untitled1/pdf/testPDF.pdf");
        controller.getHeaderText().setText(pdfContent[0]); // Sets header to pdf file name
        controller.getTextArea().setText(pdfContent[1]); // Sets text area to the contents of the pdf file




    }

    public static void main(String[] args) {
        launch();
    }
}
