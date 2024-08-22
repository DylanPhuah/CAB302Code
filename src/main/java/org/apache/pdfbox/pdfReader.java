package org.apache.pdfbox;/*
---------------------------------------------- pdfbox class to read pdf files ----------------------------------------------------------------
 */
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class pdfReader {
    public static void main(String args[]) throws IOException{
        // Loads a document from the chosen directory
        File file = new File("C:/Users/Juan/IdeaProjects/untitled1/pdf/testPDF.pdf");
        PDDocument document = Loader.loadPDF(file);

        // Instantiate a PDFTextStripper class to access the methods required to rip text from a pdf file
        PDFTextStripper pdfStripper = new PDFTextStripper();

        // Retrieve text from the loaded document into a string variable "text" and print it to the console
        String text = pdfStripper.getText(document);
        System.out.println(text);

        // Close the document
        document.close();

        //Experimental: split the text variable into different sections, example below is trying to split by paragraphs
        String[] sections = text.split("\n\\s*\n");
        System.out.print("Sections found: " + sections.length);
        // Will need work to separate a document by pages

    }


}
