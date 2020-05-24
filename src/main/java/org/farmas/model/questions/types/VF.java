package org.farmas.model.questions.types;

public class VF {

    @Override
    public String toString() {
        return "VF{" +
                "question='" + question + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }

    // Attributes
    private final String question;
    private final boolean correctAnswer;

    // Constructor
    public VF(String question, boolean correctAnswer) {
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
}
