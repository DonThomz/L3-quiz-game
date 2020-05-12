package org.farmas.model.questions;

public class Question {

    // Attributes

    private final int id;
    private final String theme;
    private final Level level;

    // Constructor

    public Question(int id, String theme, Level level) {
        this.id = id;
        this.theme = theme;
        this.level = level;
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


}
