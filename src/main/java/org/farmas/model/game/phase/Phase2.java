package org.farmas.model.game.phase;

import org.farmas.model.players.Player;
import org.farmas.model.players.StatePlayer;
import org.farmas.model.questions.Level;
import org.farmas.model.questions.ListQuestions;
import org.farmas.model.questions.Question;
import org.farmas.model.themes.Themes;
import org.farmas.model.tools.RessourcesScanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Phase2 implements Phase {

    public static int ID_PLAYER = 0;
    public static final int POINT_BY_QUESTION = 3;
    public static final int NB_OF_THEMES = 6;
    public static final int NB_OF_QUESTIONS = 6;
    public int TURN;
    private static final String LEVEL = Level.MEDIUM.toLowerCase();
    ArrayList<Player> players;
    ListQuestions listQuestions;
    String[] themes;

    public Phase2(ArrayList<Player> players, Themes themes) {
        this.TURN = 1;
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
        this.players.get(ID_PLAYER).changeStat(StatePlayer.SELECT);
        return this.players.get(ID_PLAYER);
    }

    @Override
    public void phaseDeJeu() {
        // load questions
        loadQuestionsFromJSON();
    }

    @Override
    public void selectThemes(Themes themes) {
        this.themes = themes.selectNRandomTheme(NB_OF_THEMES);
    }

    private void loadQuestionsFromJSON() {

        // list of JSON Files
        List<JSONObject> questionsFileArray = RessourcesScanner.readJSONFilesFromRessources("questions", themes);

        Map<JSONObject, String> selectedQuestions = new HashMap<>();
        if (questionsFileArray != null) {
            questionsFileArray.forEach(questionsFile -> {
                // get "results" array
                JSONArray results = (JSONArray) questionsFile.get("results");
                for (Object result : results) {
                    // cast each question block to JSONObject
                    JSONObject question = (JSONObject) result;

                    // get all medium questions
                    if (question.get("difficulty").equals(LEVEL))
                        selectedQuestions.put(question, question.get("category").toString());

                }
            });
        }

        // setup ListQuestions with the list of easy questions
        if (selectedQuestions.size() > 0) setupListQuestions(selectedQuestions);
        else System.out.println("No questions find ! :(");

    }

    /**
     * Setup the list of questions for the round
     *
     * @param questionsByTheme map with the JSONObject corresponding to the question and a String corresponding to the question's theme
     */
    private void setupListQuestions(Map<JSONObject, String> questionsByTheme) {
        for (int i = 0; i < themes.length; i++) {
            int finalI = i;
            // get random questions in a specific theme
            List<Map.Entry<JSONObject, String>> question = questionsByTheme.entrySet().stream().filter(q -> q.getValue().equals(themes[finalI])).collect(Collectors.toList());
            // get a random question in in the List
            int randomID = new Random().nextInt(question.size());
            listQuestions.addQuestion(new Question<>(1, question.get(randomID).getKey()));
        }
    }

    /**
     * Select a question that depends on the theme
     *
     * @param theme the selected theme
     * @return the question selected
     */
    public Question<?> getQuestionByTheme(String theme) {
        for (Question<?> question : listQuestions) {
            if (question.getTheme().equals(theme)) {
                try {
                    return question;
                } finally {
                    listQuestions.getQuestions().remove(question);
                }
            }
        }
        return null;
    }

    public String[] getThemes() {
        return themes;
    }

    public LinkedList<Question<?>> getListQuestions() {
        return listQuestions.getQuestions();
    }

}
