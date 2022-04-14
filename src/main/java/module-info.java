module com.java.app.lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.java.app.lab4 to javafx.fxml;
    exports com.java.app.lab4;
}