package org.farmas.model.questions;

public class QuestionTF {

    // Attributes

    private final String question;
    private final boolean correctAnswer;

    // Constructor

    public QuestionTF(String question, boolean correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    // Getters

    public String getQuestion() {
        return question;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "QuestionTF{" +
                super.toString() + "\n" +
                "question='" + question + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }

}
