package org.farmas.controller.game.questions.types;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.farmas.controller.InitController;
import org.farmas.model.questions.Question;
import org.farmas.model.questions.types.MCQ;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MCQController implements Initializable, InitController {

    @FXML
    private Label questionLabel;
    @FXML
    private Label themeLabel;
    @FXML
    private JFXButton answer1;
    @FXML
    private JFXButton answer2;
    @FXML
    private JFXButton answer3;
    @FXML
    private JFXButton answer4;

    private int answerSelected = -1;
    private List<JFXButton> answersButtons;

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

    public void initData(Question<MCQ> question) {
        questionLabel.setText(question.getContent().getQuestion());
        themeLabel.setText(question.getTheme().contains("_") ? question.getTheme().replace("_", " ") : question.getTheme());

        // init answers
        List<String> answers = Arrays.asList(question.getContent().getCorrectAnswer(),
                question.getContent().getIncorrectAnswers().get(0),
                question.getContent().getIncorrectAnswers().get(1),
                question.getContent().getIncorrectAnswers().get(2));
        Collections.shuffle(answers);
        answersButtons = Arrays.asList(answer1, answer2, answer3, answer4);
        for (int i = 0; i < answersButtons.size(); i++) {
            answersButtons.get(i).setText(answers.get(i));
            int finalI = i;
            answersButtons.get(i).setOnAction(event -> {
                answerSelected = finalI;
                updateBackgroundColorButton(answersButtons, answersButtons.get(finalI));
            });
        }
    }

    public boolean checkAnswer(Question<MCQ> question) {
        return (answersButtons != null && answersButtons.get(answerSelected).getText().equals(question.getContent().getCorrectAnswer()));
    }

    public void updateBackgroundColorButton(List<JFXButton> answersButtons, JFXButton selectedButton) {
        answersButtons.forEach(button -> button.setStyle(button.equals(selectedButton) ? "-fx-background-color: #ffb300;" : "-fx-background-color: #212121;"));
    }

    public boolean checkIfButtonSelected() {
        return answerSelected != -1;
    }

    public void displayCorrection(Question<MCQ> question) {
        if (!answersButtons.get(answerSelected).getText().equals(question.getContent().getCorrectAnswer())) {
            answersButtons.get(answerSelected).setStyle("-fx-background-color: #d32f2f;");
        }
        answersButtons.forEach(button -> {
            if (button.getText().equals(question.getContent().getCorrectAnswer())) {
                button.setStyle("-fx-background-color: #4caf50;");
            }
        });
    }

}
