import com.example.main.PdfReader;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PdfReaderTest {

    PdfReader pdfReader = new PdfReader();
    @Test
    @Order(1)
    public void TestInvalidFilePath() {

        String filePath = "src/test/java/non_existing_dir/file.pdf";

        try{
            pdfReader.readPdf(filePath, "300");
            fail("Expected exception not thrown for invalid file path.");
        } catch (IOException e){
            assertEquals("File path does not exist: " + filePath, e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void TestInvalidFileType() {
        String filePath = "src/pdFile/invalid.txt";

        try{
            pdfReader.readPdf(filePath, "300");
            fail("Expected exception not thrown for invalid file type.");
        } catch (IOException e){
            assertEquals("The file is not a PDF: " + filePath, e.getMessage());
        }


    }

    @Test
    @Order(3)
    public void TestInvalidUnitCode() {
        String filePath = "src/pdFile/test.pdf";
        String unitCode = "l12";

        try{
            pdfReader.readPdf(filePath,unitCode);
            fail("Expected exception not thrown for invalid unit code.");
        } catch (IOException e){
            assertEquals("Invalid unit code: " + unitCode, e.getMessage());
        }
    }

}