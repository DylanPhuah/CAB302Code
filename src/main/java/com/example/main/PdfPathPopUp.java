package com.example.main;

import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;


public class PdfPathPopUp {
    PdfReader pdfReader = new PdfReader();

    /** Method to display two different popups,
     * allowing users to insert a file path and a unit
     * code to insert a pdf file into the database
     */
    public void displayPopup() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("File Path Input");
        dialog.setHeaderText("Enter the file path:");
        dialog.setContentText("File Path:");

        // Capture the result off the dialog
        Optional<String> result = dialog.showAndWait();

        // Sets field descriptions for the popup
        result.ifPresent(filePath -> {
            TextInputDialog numberDialog = new TextInputDialog();
            numberDialog.setTitle("Unit Input");
            numberDialog.setHeaderText("Enter Unit Code:");
            numberDialog.setContentText("Code:");

            // Capture the result of the number dialog
            Optional<String> unitCode = numberDialog.showAndWait();

            // If the user provides a number, proceed
            unitCode.ifPresent(code -> {

                try {
                    // Attempt to parse the pdf file into the database under its unit code
                    pdfReader.readPdf(filePath, code);
                } catch (IOException e) {
                    ExceptionPopUp.exceptionPopUp("Error processing the file path: " + filePath, "Error");
                }
            });
        });
    }

}
