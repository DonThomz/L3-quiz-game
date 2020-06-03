open module org.farmas {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.jfoenix;
    requires reflections;
    requires org.apache.commons.text;
    //opens org.farmas.controller.game to javafx.controls, javafx.fxml;
    //opens org.farmas.controller to javafx.controls, javafx.fxml;
    exports org.farmas;
}