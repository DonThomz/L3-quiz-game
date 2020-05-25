package org.farmas.model.game.phase;

import org.farmas.App;
import org.farmas.model.players.Player;
import org.farmas.model.questions.ListQuestions;
import org.farmas.model.themes.Themes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Phase1 implements Phase {

    private static final int NB_OF_THEMES = 1;
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
        loadQuestions();
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

    public void loadQuestions() {
        /*
            TODO | charger les questions depuis le fichier JSON selon les thèmes sélectionnés
            TODO | Mettre ça dans un thread
         */
        try {
            InputStream inputStreamQuestions = App.class.getResourceAsStream("json/questions.json");
            JSONParser parserJSON = new JSONParser();
            JSONObject questionJSON = (JSONObject) parserJSON.parse(new InputStreamReader(inputStreamQuestions, "UTF-8"));

            // get "results" array
            JSONArray results = (JSONArray) questionJSON.get("results");
            for (int i = 0; i < results.size(); i++) {
                // read each question block
                JSONObject jsonObject = (JSONObject) results.get(i);

                // display question attribute
                System.out.println(jsonObject.get("question"));
            }

        } catch (Exception ex) {
            System.out.println("Error loading JSON file");
            ex.printStackTrace();
        }

    }
}
