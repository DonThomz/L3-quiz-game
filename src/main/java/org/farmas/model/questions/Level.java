package org.farmas.model.questions;

public enum Level {
    EASY, MEDIUM, HARD;

    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
