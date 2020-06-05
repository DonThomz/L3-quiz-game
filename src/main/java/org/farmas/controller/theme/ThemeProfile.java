package org.farmas.controller.theme;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.farmas.App;
import org.farmas.controller.InitController;
import org.farmas.controller.game.GameController;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemeProfile implements Initializable, InitController {

    @FXML
    private Label title;
    @FXML
    private ImageView image;
    @FXML
    private JFXButton themeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setupListeners() {

    }

    @Override
    public void setupServices() {

    }

    public void initData(String themeName, GameController gameController) {
        setupTitleName(themeName);
        setupImage(themeName);
        themeButton.setOnAction(event -> {
            // disable view
            themeButton.setVisible(false);
            gameController.loadPlayerQuestionPhaseII(GameController.game.getPhaseII().selectPlayer(), themeName);
        });
    }

    public void setupTitleName(String name) {
        if (name.contains("_")) name = name.replace("_", " ");
        title.setText(name);
    }

    public void setupImage(String theme) {
        image.setImage(new Image(App.class.getResource("image/" + theme.toLowerCase() + ".png").toExternalForm(), true));
    }
}
