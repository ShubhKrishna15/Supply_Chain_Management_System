module com.example.suppy_chain_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.suppy_chain_management to javafx.fxml;
    exports com.example.suppy_chain_management;
}