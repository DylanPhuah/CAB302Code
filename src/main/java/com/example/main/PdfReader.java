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

    public String[] readPdf(String filePath) throws IOException{
        String[] result = new String[2];

        try {
            //Checks that the file path is valid
            if (!IsFilePathValid(filePath)) {
                throw new IOException("File path does not exist: " + filePath);
            }  else if (!IsFileTypeValid(filePath)){
                throw new IOException("The file is not a PDF: " + filePath);
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
            throw e;
        } catch (Exception e){
            // Handles unexpected exceptions and displays a popup for the user
            ExceptionPopUp.exceptionPopUp("An unexpected error has occurred: " + e.getMessage(), "Error");
            throw e;
        }

        Textbook textbook = new Textbook(result[0], "302", result[1]);
        textbookDAO.insert(textbook);
        return result;

    }

    public Boolean IsFilePathValid(String filePath){
        try {
            Paths.get(filePath);
        } catch (InvalidPathException | NullPointerException ex){
            return false;
        }
        return true;
    }

    public Boolean IsFileTypeValid(String filePath){
        return filePath.endsWith(".pdf");
    }

}



