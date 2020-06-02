module org.farmas {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.jfoenix;
    opens org.farmas to javafx.fxml;
    opens org.farmas.controller to javafx.fxml;
    exports org.farmas;
}