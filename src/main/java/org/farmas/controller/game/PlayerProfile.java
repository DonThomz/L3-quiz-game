package org.farmas.controller.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.farmas.App;
import org.farmas.model.players.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerProfile implements Initializable {

    @FXML private Label titlePlayer;
    @FXML private ImageView profilePlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profilePlayer.setImage(new Image(App.class.getResource("image/profile.png").toExternalForm(), true));
    }

    public void setupTitleName(String name){
        titlePlayer.setText(name);
    }


}
