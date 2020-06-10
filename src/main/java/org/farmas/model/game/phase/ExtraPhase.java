package org.farmas.model.game.phase;

import org.farmas.model.players.Player;
import org.farmas.model.questions.Level;
import org.farmas.model.questions.ListQuestions;
import org.farmas.model.questions.Question;
import org.farmas.model.themes.Themes;
import org.farmas.model.tools.RessourcesScanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class ExtraPhase implements Phase {

    public static int ID_PLAYER = 0;
    public static int POINT_BY_QUESTION = 1;
    public static int NB_OF_THEMES = 10;
    public static int NB_OF_QUESTIONS = 3;
    public static String LEVEL;
    public int TURN;
    ArrayList<Player> players;
    Map<Player, Integer> oldScores;
    ListQuestions listQuestions;
    String[] themes;

    public ExtraPhase(ArrayList<Player> players, int ROUND, Themes themes) {
        ID_PLAYER = 0;
        TURN = 1;
        this.players = players; // shallow copy
        this.oldScores = new HashMap<>();
        players.forEach(player -> {
            this.oldScores.put(player, player.getScore());
        });
        this.listQuestions = new ListQuestions();
        selectThemes(themes);
        switch (ROUND) {
            case 1:
                LEVEL = Level.EASY.toLowerCase();
                break;
            case 2:
                LEVEL = Level.MEDIUM.toLowerCase();
                break;
            case 3:
                LEVEL = Level.HARD.toLowerCase();
                break;
        }
        phaseDeJeu();

        listQuestions.forEach(System.out::println);
    }

    @Override
    public Player selectPlayer() {
        return players.get(ID_PLAYER);
    }

    @Override
    public void phaseDeJeu() {
        // load questions
        loadQuestionsFromJSON();
    }

    @Override
    public void selectThemes(Themes themes) {
        this.themes = themes.selectNRandomTheme(10);
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

    public Player getPlayerToBeRemove(Map<Player, Long> mapTimer) {
        List<Integer> scores = this.players.stream().map(Player::getScore).collect(Collectors.toList());
        int minScore = Collections.min(scores);
        int conflict = (int) scores.stream().filter(score -> score == minScore).count();
        if (conflict > 1) {
            // compare timer
            long minTime = Collections.min(mapTimer.values());
            int conflictTimer = (int) mapTimer.values().stream().filter(delay -> delay == minTime).count();
            if (conflictTimer > 1) {
                // get player to be remove
                return this.getPlayers().get(new Random().nextInt(this.players.size()));
            } else {
                // get randomly a player to be remove
                return mapTimer.entrySet().stream().filter((player) -> player.getValue() == minTime).map(Map.Entry::getKey).collect(Collectors.toList()).get(0);
            }
        } else {
            // get the player with the lowest score
            return mapTimer.keySet().stream().filter(aLong -> aLong.getScore() == minScore).collect(Collectors.toList()).get(0);
        }
    }

    public void replaceOldScore(ArrayList<Player> players) {
        players.forEach(player -> {
            if (oldScores.containsKey(player)) {
                player.setScore(oldScores.get(player));
            }
        });
    }

    public LinkedList<Question<?>> getListQuestions() {
        return listQuestions.getQuestions();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
