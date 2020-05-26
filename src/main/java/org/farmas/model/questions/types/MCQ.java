package org.farmas.model.questions.types;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MCQ implements TypeQuestions {

    // Attributes
    private final String question;
    private final String correctAnswer;
    private final ArrayList<String> incorrectAnswers;

    // Constructor
    public MCQ(String question, String correctAnswer, String... incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = new ArrayList<>(Arrays.asList(incorrectAnswers)); // deep copy
    }

    public MCQ(JSONObject question) {

        this.question = question.get("question").toString();
        this.correctAnswer = question.get("correct_answer").toString();

        this.incorrectAnswers = new ArrayList<>();
        JSONArray incorrectAnswersJSON = (JSONArray) question.get("incorrect_answers");
        for (Object o : incorrectAnswersJSON) {
            this.incorrectAnswers.add(o.toString());
        }

    }


    @Override
    public String toString() {
        return "MCQ{" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers +
                '}';
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

}
