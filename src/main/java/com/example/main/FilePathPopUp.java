package com.example.main;

import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;


public class FilePathPopUp {
    PdfReader pdfReader = new PdfReader();

    public void displayPopup(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("File Path Input");
        dialog.setHeaderText("Enter the file path:");
        dialog.setContentText("File Path:");

        // Capture the result off the dialog
        Optional<String> result = dialog.showAndWait();


        result.ifPresent(filePath -> {
            try {
                // Attempt to parse the pdf file into the database
                pdfReader.readPdf(filePath);
            } catch (IOException e) {
                ExceptionPopUp.exceptionPopUp("Error processing the file path: " + filePath, "Error");
            }
        });
    }

}
