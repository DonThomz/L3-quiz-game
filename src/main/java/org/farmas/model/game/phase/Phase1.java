package org.farmas.model.game.phase;

import org.farmas.model.players.Player;
import org.farmas.model.questions.ListQuestions;
import org.farmas.model.questions.Question;
import org.farmas.model.themes.Themes;
import org.farmas.model.tools.RessourcesScanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Phase1 implements Phase {

    private static final int NB_OF_THEMES = 10;
    private static final int NB_OF_QUESTIONS = 10;
    ArrayList<Player> players;
    ListQuestions listQuestions;
    String[] themes;

    public Phase1(ArrayList<Player> players, Themes themes) {
        this.players = players; // shallow copy
        this.listQuestions = new ListQuestions();
        this.themes = new String[NB_OF_THEMES];

        // pick themes
        selectThemes(themes);

        // load questions
        loadQuestionsFromJSON();

        this.listQuestions.forEach(System.out::println);
    }

    @Override
    public void selectPlayer() {

    }

    @Override
    public void phaseDeJeu() {
        // load questions

    }

    @Override
    public void selectThemes(Themes themes) {
        for (int i = 0; i < NB_OF_THEMES; i++) {
            this.themes[i] = themes.selectTheme();
            System.out.println(this.themes[i]);
        }
    }

    private void loadQuestionsFromJSON() {
        /*
            TODO | Mettre ça dans un thread
         */

        // list of JSON Files
        List<JSONObject> questionsFileArray = RessourcesScanner.readJSONFilesFromRessources("questions", themes);

        ArrayList<JSONObject> easyQuestions = new ArrayList<>();
        if (questionsFileArray != null) {
            questionsFileArray.forEach(questionsFile -> {
                // get "results" array
                JSONArray results = (JSONArray) questionsFile.get("results");
                for (Object result : results) {
                    // cast each question block to JSONObject
                    JSONObject question = (JSONObject) result;

                    // get all easy questions
                    if (question.get("difficulty").equals("easy")) easyQuestions.add(question);
                }
            });
        }

        // setup ListQuestions with the list of easy questions
        setupListQuestions(easyQuestions);

    }

    private void setupListQuestions(ArrayList<JSONObject> questions) {
        ArrayList<Integer> nbRandom = new ArrayList<>();
        for (int i = 0; i < NB_OF_QUESTIONS; i++) {
            int randomNb = 0;
            do {
                randomNb = new Random().nextInt(questions.size());
            } while (nbRandom.contains(randomNb));
            nbRandom.add(randomNb);

            // create new Question object
            listQuestions.addQuestion(new Question<>(i, questions.get(randomNb)));

        }
    }

}
