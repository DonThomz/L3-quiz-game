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
        //URL url = getClass().getResource("profile.png");
        //System.out.println(url);
        //profilePlayer.setImage(new Image("profile.png"));
    }

    public void setupTitleName(String name){
        titlePlayer.setText(name);
    }

    public static PlayerProfile loadProfile(String name){
        PlayerProfile playerProfile = new PlayerProfile();
        playerProfile.setupTitleName(name);
        return playerProfile;
    }

}
