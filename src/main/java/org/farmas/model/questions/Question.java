package org.farmas.model.questions;

import org.farmas.model.questions.types.MCQ;
import org.farmas.model.questions.types.SA;
import org.farmas.model.questions.types.TF;
import org.farmas.model.questions.types.TypeQuestions;
import org.json.simple.JSONObject;

public class Question<T extends TypeQuestions> {

    // Attributes
    private int id;
    private String theme;
    private Level level;
    private T content;

    // Constructor
    public Question(int id, String theme, Level level, T content) {
        this.id = id;
        this.theme = theme;
        this.level = level;
        this.content = content;
    }

    @SuppressWarnings("unchecked")
    public Question(int id, JSONObject jsonQuestion) {
        this.id = id;
        this.theme = jsonQuestion.get("category").toString();
        this.level = Level.valueOf(jsonQuestion.get("difficulty").toString().toUpperCase());
        switch (jsonQuestion.get("type").toString()) {
            case "multiple":
                this.content = (T) new MCQ(jsonQuestion);
                break;
            case "short_answer":
                this.content = (T) new SA(jsonQuestion);
                break;
            case "boolean":
                this.content = (T) new TF(jsonQuestion);
                break;
        }
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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", level=" + level +
                ", content=" + content +
                '}';
    }
}
