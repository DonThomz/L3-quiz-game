package org.farmas.controller.rules;

import com.jfoenix.controls.JFXButton;
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
    public Label titlerules;
    @FXML
    public Label textrules;
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
        quitButton.setOnAction(event ->{
            try {
                App.setScene("home");
                App.window.centerOnScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        phase3link.setOnAction(event -> {
            hyperlinkVisited(3);
            titlerules.setText("Explication de la phase 3");
            textrules.setText("Nos deux derniers joueurs vont pouvoir se départagé lors d'une serie \nde 6 question de niveau : DIFFICILE \n" +
                    "Le thème de ces questions sera choisis aléatoirement !\n\n" +
                    "Qui sera donc le grand gagnant du QUIZZ ? A vous de jouer ! \n" +
                    "Bonne chance ");
        });
        phase2link.setOnAction(event -> {
            hyperlinkVisited(2);
            titlerules.setText("Explication de la phase 2");
            textrules.setText("Les joueurs ayant gagné lors de la phase précedente vont pouvoir \n" +
                    "tour à tour choisir un thème de réponse \n " +
                    "   -> Celui qui à eu le score(ou le temps)  le plus élévé commence ! \n\n" +
                    "Les questions proposé seront de niveau MOYEN, celui qui à la fin des \n" +
                    " 6 questions aura le score le plus faible ce verra éliminé !");
        });
        phase1link.setOnAction(event -> {
            hyperlinkVisited(1);
            titlerules.setText("Explication de la phase 1");
            textrules.setText("Nos quatres joueurs vont être chacun confronté à une question de \n niveau : FACILE \n" +
                    "Chaque bonne réponse vous donnera 2 points, alors soyez rapide \n " +
                    "car en cas d'égalité c'est le timer qui tranchera ! \n" +
                    "Les 3 meilleurs joueurs pourront donc accéder à la phase 2 ! ");
        });
    }

    public void hyperlinkVisited(int x){
        switch (x){
            case 1 :
                phase1link.setVisited(true);
                phase2link.setVisited(false);
                phase3link.setVisited(false);
                break;
            case 2:
                phase1link.setVisited(false);
                phase2link.setVisited(true);
                phase3link.setVisited(false);
                break;
            case 3 :
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
        titlerules.setText("Explication de la phase 1");
        textrules.setText("Nos quatres joueurs vont être chacun confronté à une question de \n niveau : FACILE \n" +
                "Chaque bonne réponse vous donnera 2 points, alors soyez rapide \n " +
                "car en cas d'égalité c'est le timer qui tranchera ! \n" +
                "Les 3 meilleurs joueurs pourront donc accéder à la phase 2 ! ");
    }

}

