package org.farmas.model.questions.types;

import java.util.Arrays;

public class MCQ {

    // Attributes
    private final String question;
    private final String correctAnswer;
    private final String[] incorrectAnswers;


    // Constructor
    public MCQ(String question, String correctAnswer, String... incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = Arrays.copyOf(incorrectAnswers, incorrectAnswers.length); // deep copy
    }

    @Override
    public String toString() {
        return "MCQ{" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + Arrays.toString(incorrectAnswers) +
                '}';
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
}
