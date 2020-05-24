package org.farmas.model.questions;

public class Question<T> {

    // Attributes
    private final int id;
    private final String theme;
    private final Level level;
    private final T content;

    // Constructor
    public Question(int id, String theme, Level level, T content) {
        this.id = id;
        this.theme = theme;
        this.level = level;
        this.content = content;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public Level getLevel() {
        return level;
    }

    public void print() {
        System.out.println("Niveau: " + this.getLevel() + "\n" + this.content.toString());
    }
}
