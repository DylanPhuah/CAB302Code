package com.example.PdfReader;

import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PdfReaderTest {
    @Test
    public void TestInvalidFilePath() {
        PdfReader pdfReader = new PdfReader();
        String filePath = "src/test/java/non_existing_dir/file.pdf";

        try{
            pdfReader.readPdf(filePath);
            fail("Expected exception not thrown for invalid file path.");
        } catch (IOException e){
            assertEquals("File path does not exist: " + filePath, e.getMessage());
        }
    }

}