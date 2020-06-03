package org.farmas.controller.game;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import org.farmas.App;
import org.farmas.controller.InitController;
import org.farmas.controller.game.questions.types.MCQController;
import org.farmas.controller.game.questions.types.SAController;
import org.farmas.controller.game.questions.types.TFController;
import org.farmas.model.game.Game;
import org.farmas.model.game.phase.Phase1;
import org.farmas.model.players.Player;
import org.farmas.model.questions.Question;
import org.farmas.model.questions.types.MCQ;
import org.farmas.model.questions.types.SA;
import org.farmas.model.questions.types.TF;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController implements Initializable, InitController {

    @FXML private JFXButton returnButton;
    @FXML private AnchorPane body;
    @FXML private HBox content;
    @FXML private Label titleStep;
    @FXML private JFXButton submitButton;

    public static Game game;
    public static ArrayList<VBox> playersProfiles;
    private FXMLLoader controllerLoader;

    public static int STEP = 0;
    public static boolean LOCK = false;
    public static int ID_PLAYER = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        App.window.sizeToScene();
        initGameData();
        setupListeners();
        setupStep();

    }

    @Override
    public void setupListeners() {

        returnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, confirmReturnMenu);

        submitButton.setOnAction(event -> launchPhaseI());

    }

    @Override
    public void setupServices() {

    }

    private void setupStep(){
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

    private void initGameData(){

        game = new Game();
        game.pickPlayers();
        loadPlayerBoard();

    }

    private void launchPhaseI() {

        STEP++;
        setupStep();
        game.runPhaseI();



        // change submit button
        submitButton.setText("Submit Answer");


        // run the first round
        loadPlayerQuestionPhaseI(game.getPlayers().get(0), 0);

    }

    private void loadPlayerQuestionPhaseI(Player player, int ID) {

        // TODO add timer
        System.out.println(ID);
        // clear center content
        clearContent();
        System.out.println(ID);
        if(ID < 4) {
            AtomicInteger IDatomic = new AtomicInteger(ID);
            switch (game.getPhaseI().getListQuestions().get(ID).getContent().getClass().getSimpleName()) {
                case "MCQ":
                    loadMQCGUI(game.getPhaseI().getListQuestions().get(ID));
                    // update submit button action
                    submitButton.setOnAction(event -> {
                        boolean isCorrect = controllerLoader.<MCQController>getController().checkAnswer((Question<MCQ>) game.getPhaseI().getListQuestions().get(IDatomic.get()));
                        player.updateScore(Phase1.POINT_BY_QUESTION, isCorrect);
                        IDatomic.getAndIncrement();
                        if(IDatomic.get() < 4 ) loadPlayerQuestionPhaseI(game.getPlayers().get(IDatomic.get()), IDatomic.get());
                        else exit();
                    });
                    break;
                case "SA":
                    loadSAGUI(game.getPhaseI().getListQuestions().get(ID));
                    // update submit button action
                    submitButton.setOnAction(event -> {
                        boolean isCorrect = controllerLoader.<SAController>getController().checkAnswer((Question<SA>) game.getPhaseI().getListQuestions().get(IDatomic.get()));
                        player.updateScore(Phase1.POINT_BY_QUESTION, isCorrect);
                        IDatomic.getAndIncrement();
                        if(IDatomic.get() < 4 ) loadPlayerQuestionPhaseI(game.getPlayers().get(IDatomic.get()), IDatomic.get());
                        else exit();
                    });
                    break;
                case "TF":
                    loadTFGUI(game.getPhaseI().getListQuestions().get(ID));
                    // update submit button action
                    submitButton.setOnAction(event -> {
                        boolean isCorrect = controllerLoader.<TFController>getController().checkAnswer((Question<TF>) game.getPhaseI().getListQuestions().get(IDatomic.get()));
                        System.out.println(isCorrect);
                        player.updateScore(Phase1.POINT_BY_QUESTION, isCorrect);
                        IDatomic.getAndIncrement();
                        if(IDatomic.get() < 4 ) loadPlayerQuestionPhaseI(game.getPlayers().get(IDatomic.get()), IDatomic.get());
                        else exit();
                    });
                    break;
            }
        } else { // next to second round
            // TODO check score
            submitButton.setOnAction(event -> {
                try {
                    App.setScene("home");
                    App.window.centerOnScreen();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }

    }

    private void exit(){
        submitButton.setOnAction(event -> {
            try {
                App.setScene("home");
                App.window.centerOnScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void loadMQCGUI(Question<?> question){
        try {
            controllerLoader = new FXMLLoader(App.class.getResource("views/mcq.fxml"));
            content.getChildren().add(0, controllerLoader.load());
            controllerLoader.<MCQController>getController().initData((Question<MCQ>) question);
        }catch (IOException ioException){
            System.err.println("Error loading question QCM fxml");
            System.err.println(ioException.getMessage());
            ioException.printStackTrace();
        }
    }

    private void loadSAGUI(Question<?> question){
        try {
            controllerLoader = new FXMLLoader(App.class.getResource("views/sa.fxml"));
            content.getChildren().add(0, controllerLoader.load());
            controllerLoader.<SAController>getController().initData((Question<SA>) question);
        }catch (IOException ioException){
            System.err.println("Error loading question QCM fxml");
            System.err.println(ioException.getMessage());
            ioException.printStackTrace();
        }
    }

    private void loadTFGUI(Question<?> question){
        try {
            controllerLoader = new FXMLLoader(App.class.getResource("views/tf.fxml"));
            content.getChildren().add(0, controllerLoader.load());
            controllerLoader.<TFController>getController().initData((Question<TF>) question);
        }catch (IOException ioException){
            System.err.println("Error loading question QCM fxml");
            System.err.println(ioException.getMessage());
            ioException.printStackTrace();
        }
    }


    private void clearContent(){
        content.getChildren().clear();
        content.setAlignment(Pos.CENTER);
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
        content.getChildren().addAll(playersProfiles);
    }

    private final EventHandler<MouseEvent> confirmReturnMenu = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Êtes-vous sûr de vouloir quitter la partie ?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Quitter");
        closeConfirmation.setHeaderText("Confirmer la fermeture");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(App.window);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (closeResponse.isPresent()) {
            if (ButtonType.OK.equals(closeResponse.get())) {
                try {
                    App.setScene("home");
                    App.window.centerOnScreen();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            event.consume();
        }
    };

}
