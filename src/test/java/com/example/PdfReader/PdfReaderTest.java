package com.example.PdfReader;

import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PdfReaderTest {

    PdfReader pdfReader = new PdfReader();
    @Test
    public void TestInvalidFilePath() {

        String filePath = "src/test/java/non_existing_dir/file.pdf";

        try{
            pdfReader.readPdf(filePath);
            fail("Expected exception not thrown for invalid file path.");
        } catch (IOException e){
            assertEquals("File path does not exist: " + filePath, e.getMessage());
        }
    }

    @Test
    public void TestInvalidFileType() {
        String filePath = "src/pdFile/invalid.txt";

        try{
            pdfReader.readPdf(filePath);
            fail("Expected exception not thrown for invalid file type.");
        } catch (IOException e){
            assertEquals("The file is not a PDF: " + filePath, e.getMessage());
        }


    }

}