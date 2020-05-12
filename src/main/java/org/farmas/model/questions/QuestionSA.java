package org.farmas.model.questions;


public class QuestionSA {

    // Attributes

    private final String question;
    private final String correctAnswer;

    // Constructor

    public QuestionSA(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    // Getters
    
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
