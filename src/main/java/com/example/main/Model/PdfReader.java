/*
---------------------------------------------- pdfbox class to read pdf files ----------------------------------------------------------------
 */
package com.example.main.Model;
import com.example.main.Model.DAO.TextbookDAO;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;


public class PdfReader {

    TextbookDAO textbookDAO = new TextbookDAO();

    /** Parses a pdf file from a file path into a string and
     *  then uploads it to the database with a title and unit code.
     * @param filePath the file path to the pdf file
     * @param unitCode unit code for the pdf file to be uploaded into
     */
    public void readPdf(String filePath, String unitCode) throws IOException{
        String title;
        String text;

        try {
            //Checks that the file path is valid and the file is a pdf
            if (!IsFilePathValid(filePath)) {
                throw new IOException("File path does not exist: " + filePath);
            }  else if (!IsFileTypeValid(filePath)){
                throw new IOException("The file is not a PDF: " + filePath);
            }
            // Loads a document from the chosen directory
            File file = new File(filePath);
            PDDocument document = Loader.loadPDF(file);

            // Pulls file name into a string that is modified to create a title
            String fileName = file.getName();
            fileName = fileName.replace(".pdf", "");

            PDFTextStripper pdfStripper = new PDFTextStripper();
            title = fileName;
            text = pdfStripper.getText(document);
            document.close();

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

        Textbook textbook = new Textbook(title, unitCode, text);
        //Inserts the textbook into the database
        textbookDAO.insert(textbook);


    }

    /** Boolean method that checks that a file path exists
     * @param filePath file path to be validated
     * @return true or false
     */
    public Boolean IsFilePathValid(String filePath){
        try {
            Paths.get(filePath);
        } catch (InvalidPathException | NullPointerException ex){
            return false;
        }
        return true;
    }

    /** Boolean method that checks if a file is a pdf
     * @param filePath file path to the file to be validated
     * @return true or false
     */
    public Boolean IsFileTypeValid(String filePath){
        return filePath.endsWith(".pdf");
    }

}



