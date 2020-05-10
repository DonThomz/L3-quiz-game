module org.farmas {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.farmas to javafx.fxml;
    exports org.farmas;
}