module com.example.oopguitest {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oopguitest to javafx.fxml;
    exports com.example.oopguitest;
}