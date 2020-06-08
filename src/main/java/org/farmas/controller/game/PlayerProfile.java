package org.farmas.controller.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.farmas.App;
import org.farmas.controller.InitController;
import org.farmas.model.players.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerProfile implements Initializable, InitController {

    @FXML
    private Label title;
    @FXML
    private ImageView image;
    @FXML
    private Label titleScore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image(App.class.getResource("image/profile.png").toExternalForm(), true));

    }

    @Override
    public void setupListeners() {

    }

    @Override
    public void setupServices() {

    }

    public void setupTitleName(String name) {
        title.setText(name);
    }

    public void setupScore(int score) {
        titleScore.setText("Score : " + score);
    }

    public void initData(Player player) {
        setupTitleName(player.getId() + " " + player.getName());
        setupScore(player.getScore());
    }


}
