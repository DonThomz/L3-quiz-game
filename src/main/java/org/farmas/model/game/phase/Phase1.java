package org.farmas.model.game.phase;

import org.farmas.model.game.Game;
import org.farmas.model.players.Player;
import org.farmas.model.questions.Level;
import org.farmas.model.questions.ListQuestions;
import org.farmas.model.questions.Question;
import org.farmas.model.themes.Themes;
import org.farmas.model.tools.RessourcesScanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Phase1 implements Phase {

    public static int ID_PLAYER = 0;
    public static final int POINT_BY_QUESTION = 2;
    private static final int NB_OF_THEMES = Game.MAX_PLAYERS;
    private static final int NB_OF_QUESTIONS = Game.MAX_PLAYERS;
    private static String LEVEL = Level.EASY.toLowerCase();
    ArrayList<Player> players;
    ListQuestions listQuestions;
    String[] themes;

    public Phase1(ArrayList<Player> players, Themes themes) {
        ID_PLAYER = 0;
        this.players = players; // shallow copy
        this.listQuestions = new ListQuestions();
        this.themes = new String[NB_OF_THEMES];
        // pick themes
        selectThemes(themes);

        phaseDeJeu();

        listQuestions.forEach(System.out::println);

    }

    @Override
    public Player selectPlayer() {
        return this.players.get(ID_PLAYER);
    }

    @Override
    public void phaseDeJeu() {
        // load questions
        loadQuestionsFromJSON();
    }

    @Override
    public void selectThemes(Themes themes) {
        for (int i = 0; i < NB_OF_THEMES; i++) {
            this.themes[i] = themes.selectTheme();
        }
    }

    private void loadQuestionsFromJSON() {
        /*
            TODO | Mettre ça dans un thread
         */

        // list of JSON Files
        List<JSONObject> questionsFileArray = RessourcesScanner.readJSONFilesFromRessources("questions", themes);

        ArrayList<JSONObject> selectedQuestions = new ArrayList<>();
        if (questionsFileArray != null) {
            questionsFileArray.forEach(questionsFile -> {
                // get "results" array
                JSONArray results = (JSONArray) questionsFile.get("results");
                for (Object result : results) {
                    // cast each question block to JSONObject
                    JSONObject question = (JSONObject) result;

                    // get all easy questions
                    if (question.get("difficulty").equals(LEVEL)) selectedQuestions.add(question);
                }
            });
        }

        // setup ListQuestions with the list of easy questions
        if (selectedQuestions.size() > 0) setupListQuestions(selectedQuestions);
        else System.out.println("No questions find ! :(");

    }

    private void setupListQuestions(ArrayList<JSONObject> questions) {
        ArrayList<Integer> nbRandom = new ArrayList<>();
        for (int i = 0; i < NB_OF_QUESTIONS; i++) {
            int randomNb;
            do {
                randomNb = new Random().nextInt(questions.size());
            } while (nbRandom.contains(randomNb));
            nbRandom.add(randomNb);

            // create new Question object
            listQuestions.addQuestion(new Question<>(i, questions.get(randomNb)));

        }
    }

    public LinkedList<Question<?>> getListQuestions() {
        return listQuestions.getQuestions();
    }

}
