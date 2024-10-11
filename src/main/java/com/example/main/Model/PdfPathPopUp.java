package com.example.main.Model;

import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;


public class PdfPathPopUp {
    PdfReader pdfReader = new PdfReader();

    /** Method to display two different popups,
     * allowing users to insert a file path and a unit
     * code to insert a pdf file into the database
     */
    public void displayPopup(String UnitCode) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("File Path Input");
        dialog.setHeaderText("Enter the file path:");
        dialog.setContentText("File Path:");

        // Capture the result off the dialog
        Optional<String> result = dialog.showAndWait();

        // Sets field descriptions for the popup
        result.ifPresent(filePath -> {
            // If the user provides a number, proceed
            try {
                // Attempt to parse the pdf file into the database under its unit code
                pdfReader.readPdf(filePath, UnitCode);
            } catch (IOException e) {
                ExceptionPopUp.exceptionPopUp("Error processing the file path: " + filePath, "Error");
            }
        });
    }

}
