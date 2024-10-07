/*
---------------------------------------------- pdfbox class to read pdf files ----------------------------------------------------------------
 */
package com.example.main;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;


public class PdfReader {

    TextbookDAO textbookDAO = new TextbookDAO();

    /** Parses a pdf file from a file path into a string and then uploads it to the database with a title and unit code */
    public void readPdf(String filePath, String unitCode) throws IOException{
        String[] result = new String[2];

        try {
            //Checks that the file path is valid
            if (!IsFilePathValid(filePath)) {
                throw new IOException("File path does not exist: " + filePath);
            }  else if (!IsFileTypeValid(filePath)){
                throw new IOException("The file is not a PDF: " + filePath);
            }
            else if (!IsUnitCodeValid(unitCode)){
                throw new IOException("Invalid unit code: " + unitCode);
            }
            // Loads a document from the chosen directory
            File file = new File(filePath);
            PDDocument document = Loader.loadPDF(file);

            // Pulls file name into a string
            String fileName = file.getName();
            fileName = fileName.replace(".pdf", "");

            // Instantiate a PDFTextStripper class to access the methods required to rip text from a pdf file
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Retrieve text from the loaded document into a string variable "text" and print it to the console
            String text = pdfStripper.getText(document);

            // Close the document
            document.close();

            // Experimental: split the text variable into different sections, example below is trying to split by paragraphs
            String[] sections = text.split("\n\\s*\n");
            System.out.print(sections.length);

            result[0] = fileName;
            result[1] = text;

            // Handles any exceptions thrown by the method
        } catch (IOException e) {
            if (!IsFilePathValid(filePath)) {
                // Displays an error popup for an invalid file path
                ExceptionPopUp.exceptionPopUp("File path does not exist: " + filePath, "Error");
            } else if (!IsFileTypeValid(filePath)) {
                // Displays an error popup for an invalid file type
                ExceptionPopUp.exceptionPopUp("The file is not a PDF: " + filePath, "Error");
            }
            else if (!IsUnitCodeValid(unitCode)){
                // Displays an error popup for an invalid unit code
                ExceptionPopUp.exceptionPopUp("The unit code is invalid: " + unitCode, "Error");
            }
            throw e;
        } catch (Exception e){
            // Handles unexpected exceptions and displays a popup for the user
            ExceptionPopUp.exceptionPopUp("An unexpected error has occurred: " + e.getMessage(), "Error");
            throw e;
        }

        // Creates a new texbook with the provided details
        Textbook textbook = new Textbook(result[0], unitCode, result[1]);
        //Inserts the textbook into the database
        textbookDAO.insert(textbook);

    }

    /** Boolean method that checks that a file path exists */
    public Boolean IsFilePathValid(String filePath){
        try {
            Paths.get(filePath);
        } catch (InvalidPathException | NullPointerException ex){
            return false;
        }
        return true;
    }

    /** Boolean method that checks if the unit code is structurally correct */
    public Boolean IsUnitCodeValid(String unitCode){
        return unitCode.length() == 3 && unitCode.matches("\\d{3}");
    }

    /** Boolean method that checks if a file is a pdf */
    public Boolean IsFileTypeValid(String filePath){
        return filePath.endsWith(".pdf");
    }

}



