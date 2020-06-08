package org.farmas.controller.game.questions.types;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.farmas.controller.InitController;
import org.farmas.model.questions.Question;
import org.farmas.model.questions.types.SA;

import java.net.URL;
import java.util.ResourceBundle;

public class SAController implements Initializable, InitController {

    @FXML
    private Label questionLabel;
    @FXML
    private Label themeLabel;
    @FXML
    private JFXTextField shortAnswerField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setupListeners() {

    }

    @Override
    public void setupServices() {

    }

    public void initData(Question<SA> question) {
        questionLabel.setText(question.getContent().getQuestion());
        themeLabel.setText(question.getTheme().contains("_") ? question.getTheme().replace("_", " ") : question.getTheme());
        shortAnswerField.setPromptText("Enter a short answer");
    }

    public boolean checkAnswer(Question<SA> question) {
        return shortAnswerField.getText().toUpperCase().equals(question.getContent().getCorrectAnswer().toUpperCase()) || shortAnswerField.getText().contains(question.getContent().getCorrectAnswer());
    }

    public boolean checkIfButtonSelected() {
        return !shortAnswerField.getText().isEmpty();
    }


    public void displayCorrection(Question<SA> question) {
        if (checkAnswer(question)) {
            shortAnswerField.setStyle("-fx-background-color: #4caf50;");
        } else {
            shortAnswerField.setStyle("-fx-background-color: #d32f2f;");
        }
    }
}
