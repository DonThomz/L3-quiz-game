package org.farmas.controller.game.questions.types;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.farmas.controller.InitController;
import org.farmas.model.questions.Question;
import org.farmas.model.questions.types.TF;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TFController implements Initializable, InitController {

    @FXML
    private Label questionLabel;
    @FXML
    private Label themeLabel;
    @FXML
    private JFXButton trueButton;
    @FXML
    private JFXButton falseButton;


    private List<JFXButton> answersButtons;
    private int answerSelected = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        answersButtons = Arrays.asList(trueButton, falseButton);
    }

    @Override
    public void setupListeners() {


    }

    @Override
    public void setupServices() {

    }

    public void initData(Question<TF> question) {
        questionLabel.setText(question.getContent().getQuestion());
        themeLabel.setText(question.getTheme().contains("_") ? question.getTheme().replace("_", " ") : question.getTheme());
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            answersButtons.get(i).setOnAction(event -> {
                answerSelected = finalI;
                updateBackgroundColorButton(answersButtons, answersButtons.get(finalI));
            });
        }
    }


    public boolean checkAnswer(Question<TF> tfQuestion) {
        String rep = String.valueOf(tfQuestion.getContent().isCorrectAnswer());
        return rep.equals(answersButtons.get(answerSelected).getText().toLowerCase());
    }

    public void updateBackgroundColorButton(List<JFXButton> answersButtons, JFXButton selectedButton) {
        answersButtons.forEach(button -> button.setStyle(button.equals(selectedButton) ? "-fx-background-color: #ffb300;" : "-fx-background-color: #212121;"));
    }

    public boolean checkIfButtonSelected() {
        return answerSelected != -1;
    }

    public void displayCorrection(Question<TF> question) {
        String rep = String.valueOf(question.getContent().isCorrectAnswer());
        if (answersButtons.get(answerSelected).getText().toLowerCase().equals(rep)) {
            answersButtons.get(answerSelected).setStyle("-fx-background-color: #4caf50;");
        } else {
            answersButtons.get(answerSelected).setStyle("-fx-background-color: #d32f2f;");
        }
    }
}
