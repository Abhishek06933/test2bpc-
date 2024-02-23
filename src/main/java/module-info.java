module com.example.test2bpc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.test2bpc to javafx.fxml;
    exports com.example.test2bpc;
}