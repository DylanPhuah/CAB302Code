module com.example.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires freetts;
    requires java.desktop;
    requires org.apache.pdfbox;

    opens com.example.PdfReader to javafx.fxml;
    exports com.example.PdfReader to javafx.graphics;

    opens com.example.main to javafx.fxml;
    exports com.example.main;
}