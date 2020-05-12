module org.farmas {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    opens org.farmas to javafx.fxml;
    exports org.farmas;
}