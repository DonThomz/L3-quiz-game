package org.farmas.model.themes;

import org.farmas.App;
import org.farmas.model.tools.RessourcesScanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Themes {

    private static final int NB_OF_THEMES = 10;
    private final ArrayList<String> titles;
    private int indicator;


    public Themes() {

        this.titles = new ArrayList<>();

        this.indicator = -1;

        // load themes titles from json file
        getAllThemesFromJSON();

    }


    // Getters

    public ArrayList<String> getTitles() {
        return titles;
    }

    public int getIndicator() {
        return indicator;
    }

    public String getCurrentTheme() {
        return this.getTitles().get(this.getIndicator());
    }

    // Methods

    private void getAllThemesFromJSON(){
        // get file themes.json
        List<JSONObject> jsonFiles = RessourcesScanner.readJSONFilesFromRessources("themes");
        if(jsonFiles != null) {
            JSONArray themes = (JSONArray) jsonFiles.get(0).get("title_themes");
            for (int i = 0; i < NB_OF_THEMES; i++) {
                // read each question bloc
                titles.add(themes.get(i).toString());
            }
        }
    }




    public void modifyTheme(String theme, int indexTheme) {
        this.getTitles().set(indexTheme, theme);
    }

    public String selectTheme() {
        do {
            if (NB_OF_THEMES > 1) {
                int randomIndex = new Random().nextInt(NB_OF_THEMES);
                if (randomIndex != this.indicator) {
                    this.indicator = randomIndex;
                    try {
                        return this.titles.get(randomIndex);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else return this.titles.get(0);
        } while (true);
    }

    public String[] selectFiveTheme() {
        String[] themes = new String[5];
        ArrayList<Integer> randomNbPick = new ArrayList<>();
        int randomNumber;
        for (int i = 0; i < 5; i++) {
            // pick a random number
            do {
                randomNumber = new Random().nextInt(NB_OF_THEMES);
            } while (randomNbPick.contains(randomNumber)); // check if previously taken
            randomNbPick.add(randomNumber);
            themes[i] = this.titles.get(randomNumber);
        }
        return themes;
    }
}
