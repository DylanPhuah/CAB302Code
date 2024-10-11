module com.example.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires freetts;
    requires opencv;
    requires tess4j;
    requires java.desktop;
    requires org.apache.pdfbox;


    opens com.example.main to javafx.fxml;
    exports com.example.main;
    exports com.example.main.Controller;
    opens com.example.main.Controller to javafx.fxml;
    exports com.example.main.Model;
    opens com.example.main.Model to javafx.fxml;
    exports com.example.main.Model.DAO;
    opens com.example.main.Model.DAO to javafx.fxml;
}