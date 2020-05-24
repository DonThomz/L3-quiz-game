package org.farmas.model.questions.types;


public class SA {

    // Attributes
    private final String question;
    private final String correctAnswer;

    // Constructor
    public SA(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "SA{" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
