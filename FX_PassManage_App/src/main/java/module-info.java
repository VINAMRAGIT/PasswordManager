module com.example.fx_passmanage_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fx_passmanage_app to javafx.fxml;
    exports com.example.fx_passmanage_app;

    opens DataAccess to javafx.fxml;
    exports DataAccess;

    opens DataManipulate to javafx.fxml;
    exports DataManipulate;
}