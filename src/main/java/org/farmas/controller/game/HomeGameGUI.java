package org.farmas.controller.game;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.farmas.App;
import org.farmas.controller.InitController;
import org.farmas.model.game.Game;
import org.farmas.model.players.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeGameGUI implements Initializable, InitController {

    @FXML private JFXButton returnButton;
    @FXML private AnchorPane wrap;
    @FXML private HBox playerBox;
    @FXML private Label titleStep;

    public static Game game;
    public static ArrayList<VBox> playersProfiles;

    public int STEP = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupTitleStep();
        setupListeners();
        setupGame();
    }

    @Override
    public void setupListeners() {
        returnButton.setOnAction(event -> {
            try {
                App.setScene("home");
                App.window.centerOnScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    @Override
    public void setupServices() {

    }

    private void setupTitleStep(){
        switch (STEP){
            case 0:
                titleStep.setText("Players picked");
                break;
            case 1:
                titleStep.setText("Round I");
                break;
            case 2:
                titleStep.setText("Round II");
                break;
            case 3:
                titleStep.setText("Round III");
                break;
            case 4:
                titleStep.setText("End Game");
                break;
            default:
                titleStep.setText("Title loading");
                break;
        }
    }

    private void setupGame(){

        game = new Game();
        game.pickPlayers();
        loadPlayerBoard();
    }

    private void loadPlayerBoard(){
        playersProfiles = new ArrayList<>();
        game.getPlayers().forEach(player -> {
            try {

                FXMLLoader loader = new FXMLLoader(App.class.getResource("views/playerProfil.fxml"));
                playersProfiles.add(loader.load()); // add to the list
                loader.<PlayerProfile>getController().setupTitleName(player.getId() + " " + player.getName()); // setup the name


            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        playerBox.getChildren().addAll(playersProfiles);
    }

}
