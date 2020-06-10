open module org.farmas {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.jfoenix;
    requires reflections;
    requires org.apache.commons.text;
    requires org.apache.commons.lang3;

    exports org.farmas;
    exports org.farmas.model.game;
    exports org.farmas.model.game.phase;
    exports org.farmas.model.themes;
    exports org.farmas.model.questions;
    exports org.farmas.model.questions.types;
    exports org.farmas.model.tools;
    exports org.farmas.model.players;
    exports org.farmas.controller;
    exports org.farmas.controller.game;
    exports org.farmas.controller.game.questions.types;
    exports org.farmas.controller.theme;
    exports org.farmas.controller.rules;
}