package com.example.jfxpdfreader;
/*
--------------------------------------------Stage for Textbook Reader Page ----------------------------------------------------------------
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class textReaderApplication extends Application {

    PdfReader pdfReader = new PdfReader();

    @Override
    public void start(Stage stage) throws IOException {
        // Load FXML file and set the stage
        FXMLLoader fxmlLoader = new FXMLLoader(textReaderApplication.class.getResource("pdf-reader-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1415, 1000);
        stage.setTitle("Textbook Reader");
        stage.setScene(scene);
        stage.show();

        HelloController controller = fxmlLoader.getController();

        String[] pdfContent = pdfReader.readPdf("C:/Users/Juan/IdeaProjects/untitled1/pdf/testPDF.pdf");
        controller.getHeaderText().setText(pdfContent[0]);
        controller.getTextArea().setText(pdfContent[1]);




    }

    public static void main(String[] args) {
        launch();
    }
}
