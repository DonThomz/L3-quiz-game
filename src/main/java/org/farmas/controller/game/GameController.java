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
import org.farmas.model.game.phase.Phase2;
import org.farmas.model.players.Player;
import org.farmas.model.questions.Question;
import org.farmas.model.questions.types.MCQ;
import org.farmas.model.questions.types.SA;
import org.farmas.model.questions.types.TF;
import org.farmas.model.tools.TimerRound;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameController implements Initializable, InitController {

    @FXML
    private JFXButton returnButton;
    @FXML
    private AnchorPane body;
    @FXML
    private HBox content;
    @FXML
    private Label titleStep;
    @FXML
    private Label titlePlayerInfo;
    @FXML
    private JFXButton submitButton;

    private FXMLLoader controllerLoader;
    private ArrayList<Long> delaysPerPlayers;

    public static Game game;
    public static int STEP = 0;
    public static int ROUND = 0;
    public static boolean LOCK = false;
    public ArrayList<VBox> playersProfiles;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        App.window.sizeToScene();

        initData();
        setupListeners();
        loadPlayerBoard();

    }

    @Override
    public void setupListeners() {

        returnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, confirmReturnMenu);

    }

    @Override
    public void setupServices() {

    }

    public void initData() {
        delaysPerPlayers = new ArrayList<>();
        titlePlayerInfo.setVisible(false);
        game = new Game();
        game.pickPlayers();
    }


    /*===================
           Game GUI
    =====================*/
    private void updateStepLabel() {
        switch (STEP) {
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
            default:
                titleStep.setText("Title loading");
                break;
        }
    }

    private void updateRoundLabel() {
        switch (ROUND) {
            case 1:
                submitButton.setText("Start Round I");
                submitButton.setOnAction(event -> launchPhaseI());
                break;
            case 2:
                submitButton.setText("Start Round II");
                submitButton.setOnAction(event -> launchPhaseII());
                break;
            case 3:
                submitButton.setText("Start Round III");
                submitButton.setOnAction(event -> launchPhaseIII());
                break;
            case 4:
                submitButton.setText("Return Menu");
                submitButton.setOnAction(event -> exit());
                break;
            default:
                submitButton.setText("Title loading");
                break;
        }
    }


    private void loadPlayerBoard() {

        ROUND++;
        STEP = 0;
        this.clearContent();
        this.updateStepLabel();
        this.updateRoundLabel();
        this.titlePlayerInfo.setVisible(false);

        playersProfiles = new ArrayList<>();
        game.getPlayers().forEach(player -> {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("views/profile.fxml"));
                playersProfiles.add(loader.load()); // add to the list
                loader.<PlayerProfile>getController().setupTitleName(player.getId() + " " + player.getName()); // setup the name
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        content.getChildren().addAll(playersProfiles);
    }

    private void resetAttributesRound() {
        // update static attributes


        // show player name
        titlePlayerInfo.setVisible(true);

        // change submit button
        submitButton.setText("Submit Answer");
    }

    private void handleScoresAndConflicts() {
        // remove the worst player
        boolean conflict = game.removePlayer(delaysPerPlayers);
        System.out.println(conflict);
        // if no conflict launch player board
        if (conflict) this.loadPlayerBoard();
    }


    /*===================
            Phase I
    =====================*/
    private void launchPhaseI() {

        this.resetAttributesRound();

        STEP = 1;
        this.updateStepLabel();

        // run the first round
        game.runPhaseI();
        loadPlayerQuestionPhaseI(game.getPhaseI().selectPlayer());

    }

    private void loadPlayerQuestionPhaseI(Player player) {

        // Run timer in another Thread
        TimerRound timer = new TimerRound("Thread-" + player.getName());
        timer.start();

        // clear center content
        this.clearContent();

        // display player info
        this.titlePlayerInfo.setText("The player " + player.getId() + " " + player.getName() + " plays :");
        if (Phase1.ID_PLAYER < game.getPlayers().size()) {
            switch (game.getPhaseI().getListQuestions().get(Phase1.ID_PLAYER).getContent().getClass().getSimpleName()) {
                case "MCQ":
                    this.loadMQCGUI(game.getPhaseI().getListQuestions().get(Phase1.ID_PLAYER));
                    // update submit button action
                    this.submitButton.setOnAction(event -> {
                        if (this.controllerLoader.<MCQController>getController().checkIfButtonSelected()) {
                            this.getTimerScore(timer);
                            boolean isCorrect = controllerLoader.<MCQController>getController().checkAnswer((Question<MCQ>) game.getPhaseI().getListQuestions().get(Phase1.ID_PLAYER));
                            player.updateScore(Phase1.POINT_BY_QUESTION, isCorrect);
                            Phase1.ID_PLAYER++;
                            if (Phase1.ID_PLAYER < game.getPlayers().size())
                                this.loadPlayerQuestionPhaseI(game.getPhaseI().selectPlayer());
                            else this.handleScoresAndConflicts();

                        }
                    });
                    break;
                case "SA":
                    this.loadSAGUI(game.getPhaseI().getListQuestions().get(Phase1.ID_PLAYER));
                    // update submit button action
                    this.submitButton.setOnAction(event -> {
                        if (this.controllerLoader.<SAController>getController().checkIfButtonSelected()) {
                            this.getTimerScore(timer);
                            boolean isCorrect = controllerLoader.<SAController>getController().checkAnswer((Question<SA>) game.getPhaseI().getListQuestions().get(Phase1.ID_PLAYER));
                            player.updateScore(Phase1.POINT_BY_QUESTION, isCorrect);
                            Phase1.ID_PLAYER++;
                            if (Phase1.ID_PLAYER < game.getPlayers().size())
                                this.loadPlayerQuestionPhaseI(game.getPhaseI().selectPlayer());
                            else this.handleScoresAndConflicts();

                        }
                    });
                    break;
                case "TF":
                    this.loadTFGUI(game.getPhaseI().getListQuestions().get(Phase1.ID_PLAYER));
                    // update submit button action
                    this.submitButton.setOnAction(event -> {
                        if (this.controllerLoader.<TFController>getController().checkIfButtonSelected()) {
                            this.getTimerScore(timer);
                            boolean isCorrect = controllerLoader.<TFController>getController().checkAnswer((Question<TF>) game.getPhaseI().getListQuestions().get(Phase1.ID_PLAYER));
                            player.updateScore(Phase1.POINT_BY_QUESTION, isCorrect);
                            Phase1.ID_PLAYER++;
                            if (Phase1.ID_PLAYER < game.getPlayers().size())
                                this.loadPlayerQuestionPhaseI(game.getPhaseI().selectPlayer());
                            else this.handleScoresAndConflicts();
                        }
                    });
                    break;
            }
        } else this.exit();

    }


    /*===================
           Phase II
    =====================*/
    private void launchPhaseII() {

        this.resetAttributesRound();

        STEP = 2;
        this.updateStepLabel();

        // run the second round
        game.runPhaseII();
        loadPlayerQuestionPhaseII(game.getPhaseII().selectPlayer());

    }

    private void loadPlayerQuestionPhaseII(Player player) {

        // Run timer in another Thread
        TimerRound timer = new TimerRound("Thread-" + player.getName());
        timer.start();

        // clear center content
        this.clearContent();

        // display player info
        this.titlePlayerInfo.setText("The player " + player.getId() + " " + player.getName() + " plays :");

        ThemeBoard themeProfile = new ThemeBoard(Phase2.NB_OF_THEMES);
        themeProfile.initData(game.getPhaseII().getThemes());
        this.content.getChildren().add(themeProfile.getGridPane());
    }


    /*===================
           Phase III
    =====================*/
    private void launchPhaseIII() {
    }

    /*===================
        Question GUI
    =====================*/
    private void loadMQCGUI(Question<?> question) {
        try {
            controllerLoader = new FXMLLoader(App.class.getResource("views/mcq.fxml"));
            content.getChildren().add(0, controllerLoader.load());
            controllerLoader.<MCQController>getController().initData((Question<MCQ>) question);
        } catch (IOException ioException) {
            System.err.println("Error loading question QCM fxml");
            System.err.println(ioException.getMessage());
            ioException.printStackTrace();
        }
    }

    private void loadSAGUI(Question<?> question) {
        try {
            controllerLoader = new FXMLLoader(App.class.getResource("views/sa.fxml"));
            content.getChildren().add(0, controllerLoader.load());
            controllerLoader.<SAController>getController().initData((Question<SA>) question);
        } catch (IOException ioException) {
            System.err.println("Error loading question QCM fxml");
            System.err.println(ioException.getMessage());
            ioException.printStackTrace();
        }
    }

    private void loadTFGUI(Question<?> question) {
        try {
            controllerLoader = new FXMLLoader(App.class.getResource("views/tf.fxml"));
            content.getChildren().add(0, controllerLoader.load());
            controllerLoader.<TFController>getController().initData((Question<TF>) question);
        } catch (IOException ioException) {
            System.err.println("Error loading question QCM fxml");
            System.err.println(ioException.getMessage());
            ioException.printStackTrace();
        }
    }

    /*===================
        extra methods
    =====================*/
    private void getTimerScore(TimerRound timerRound) {
        System.out.println(timerRound.getName());
        timerRound.stopTimer();
        delaysPerPlayers.add(timerRound.getTime());
        timerRound.interrupt();
    }

    private void clearTimerScore() {
        delaysPerPlayers.clear();
    }

    private void clearContent() {
        content.getChildren().clear();
        content.setAlignment(Pos.CENTER);
    }

    private void exit() {
        game.getPlayers().forEach(p -> System.out.println(p.getName() + " " + p.getScore()));
        delaysPerPlayers.forEach(System.out::println);
        STEP = 0;
        ROUND = 0;
        try {
            App.setScene("home");
            App.window.centerOnScreen();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        submitButton.setOnAction(event -> launchPhaseI());
    }

    /*===================
        Event methods
    =====================*/
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
                exit();
            }
            event.consume();
        }
    };

}
