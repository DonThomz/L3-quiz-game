package org.farmas.controller.rules;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import org.farmas.App;
import org.farmas.controller.InitController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Rules implements Initializable, InitController {
    @FXML
    public Label titleRule;
    @FXML
    public JFXTextArea textRule;
    @FXML
    public Hyperlink phase3link;
    @FXML
    public Hyperlink phase2link;
    @FXML
    public Hyperlink phase1link;
    @FXML
    private JFXButton playButton;
    @FXML
    private JFXButton quitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupListeners();
        initData();
    }

    @Override
    public void setupListeners() {
        playButton.setOnAction(event -> {
            try {
                App.setScene("game");
                App.window.centerOnScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        quitButton.setOnAction(event -> {
            try {
                App.setScene("home");
                App.window.centerOnScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        phase3link.setOnAction(event -> {
            hyperlinkVisited(3);
            titleRule.setText("Round 3");
            textRule.setText("Our last two players will be able to decide in a series of 6 questions of level: DIFFICULTY\n" +
                    "The theme of these questions will be chosen randomly!\n" +
                    "So who will be the big winner of the QUIZ? It's up to you! \n" +
                    "Good luck!");
        });
        phase2link.setOnAction(event -> {
            hyperlinkVisited(2);
            titleRule.setText("Round 2");
            textRule.setText("Players who won in the previous phase will take turns choosing an answer theme.\n" +
                    "The proposed questions will be of MEDIUM level, the one who has the lowest score at the end of the 6 questions will be eliminated!");
        });
        phase1link.setOnAction(event -> {
            hyperlinkVisited(1);
            titleRule.setText("Round 1");
            textRule.setText("Our four players will each be confronted with a question of level: EASY \n" +
                    "Each correct answer will give you 2 points, so be quick, because in case of a tie, the timer will decide!\n" +
                    "The 3 best players will then be able to reach the second round!");
        });
    }

    public void hyperlinkVisited(int x) {
        switch (x) {
            case 1:
                phase1link.setVisited(true);
                phase2link.setVisited(false);
                phase3link.setVisited(false);
                break;
            case 2:
                phase1link.setVisited(false);
                phase2link.setVisited(true);
                phase3link.setVisited(false);
                break;
            case 3:
                phase1link.setVisited(false);
                phase2link.setVisited(false);
                phase3link.setVisited(true);
                break;
        }
    }

    @Override
    public void setupServices() {
    }

    public void initData() {
        hyperlinkVisited(1);
        titleRule.setText("Round I");
        textRule.setText("Our four players will each be confronted with a question of level: EASY \n" +
                "Each correct answer will give you 2 points, so be quick, because in case of a tie, the timer will decide!\n" +
                "The 3 best players will then be able to reach the second round!");
    }

}

