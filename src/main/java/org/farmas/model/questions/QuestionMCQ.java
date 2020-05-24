package org.farmas.model.questions;

import java.util.Arrays;

public class QuestionMCQ {

    // Attributes

    private final String question;
    private final String correctAnswer;
    private final String[] incorrectAnswers;


    // Constructor

    public QuestionMCQ(String question, String correctAnswer, String... incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = Arrays.copyOf(incorrectAnswers, incorrectAnswers.length); // deep copy
    }

    // Getters

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }

    @Override
    public String toString() {
        return "QuestionMCQ{" +
                super.toString() + "\n" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + Arrays.toString(incorrectAnswers) +
                '}';
    }
}
