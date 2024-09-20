module com.example.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires freetts;
    requires opencv;
    requires tess4j;
    requires java.desktop;


    opens com.example.main to javafx.fxml;
    exports com.example.main;
}