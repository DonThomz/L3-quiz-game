package org.farmas.model.questions.types;


import org.json.simple.JSONObject;

public class SA implements TypeQuestions {

    // Attributes
    private final String question;
    private final String correctAnswer;

    // Constructor
    public SA(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    public SA(JSONObject question) {
        this.question = question.get("question").toString();
        this.correctAnswer = question.get("correct_answer").toString();
    }

    @Override
    public String toString() {
        return "SA{" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }

    // Getters
    @Override
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

}
