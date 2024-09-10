/*
---------------------------------------------- pdfbox class to read pdf files ----------------------------------------------------------------
 */
package com.example.PdfReader;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfReader {

    public String[] readPdf(String filePath) throws IOException{
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

        return new String[]{fileName, text};
    }


}

