module com.example.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires freetts;


    opens com.example.main to javafx.fxml;
    exports com.example.main;
}