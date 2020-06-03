package org.farmas.controller.game.questions.types;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.farmas.App;
import org.farmas.controller.InitController;
import org.farmas.model.players.Player;
import org.farmas.model.questions.Question;
import org.farmas.model.questions.types.MCQ;

import java.net.URL;
import java.util.*;

public class MCQController implements Initializable, InitController {

    @FXML private Label questionLabel;
    @FXML private Label themeLabel;
    @FXML private JFXButton answer1;
    @FXML private JFXButton answer2;
    @FXML private JFXButton answer3;
    @FXML private JFXButton answer4;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionLabel.setWrapText(true);
    }

    @Override
    public void setupListeners() {
    }

    @Override
    public void setupServices() {

    }

    public void initData(Question<MCQ> question){
        questionLabel.setText(question.getContent().getQuestion());
        themeLabel.setText(question.getTheme());

        // init answers
        List<String> answers = Arrays.asList(question.getContent().getCorrectAnswer(),
                question.getContent().getIncorrectAnswers().get(0),
                question.getContent().getIncorrectAnswers().get(1),
                question.getContent().getIncorrectAnswers().get(2));
        Collections.shuffle(answers);
        List<JFXButton> answersButtons = Arrays.asList(answer1, answer2, answer3, answer4);
        for (int i = 0; i < answersButtons.size(); i++) {
            answersButtons.get(i).setText(answers.get(i));
            int finalI = i;
            answersButtons.get(i).setOnAction(event -> {
                updateBackgroundColorButton(answersButtons, answersButtons.get(finalI));
            });
        }
    }

    public void updateBackgroundColorButton(List<JFXButton> answersButtons, JFXButton selectedButton){
        answersButtons.forEach(button -> button.setStyle(button.equals(selectedButton) ? "-fx-background-color: #ffb300;" : "-fx-background-color: #212121;"));
    }


}
