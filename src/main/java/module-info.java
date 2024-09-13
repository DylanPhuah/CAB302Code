module com.example.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;
    requires java.desktop;


    opens com.example.main to javafx.fxml;
    exports com.example.main;
    exports com.example.PdfReader to javafx.graphics, javafx.fxml;
    opens com.example.PdfReader to javafx.fxml;
}