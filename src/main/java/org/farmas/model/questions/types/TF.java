package org.farmas.model.questions.types;

import org.json.simple.JSONObject;

public class TF implements TypeQuestions {


    // Attributes
    private final String question;
    private final boolean correctAnswer;

    // Constructor
    public TF(String question, boolean correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public TF(JSONObject question) {
        this.question = question.get("question").toString();
        this.correctAnswer = question.get("correct_answer").toString().equals("true");
    }

    // Getters
    @Override
    public String getQuestion() {
        return question;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "VF{" +
                "question='" + question + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }

}
