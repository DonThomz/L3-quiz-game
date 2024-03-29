package org.farmas.model.themes;

import org.farmas.model.tools.RessourcesScanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Themes implements Iterable<String> {

    private static final int NB_OF_THEMES = 10;
    private final ArrayList<String> titles;
    private static int indicator = 0;


    public Themes() {

        this.titles = new ArrayList<>();

        // load themes titles from json file
        getAllThemesFromJSON();

    }

    @Override
    public Iterator<String> iterator() {
        return titles.iterator();
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

    private void getAllThemesFromJSON() {
        // get file themes.json
        List<JSONObject> jsonFiles = RessourcesScanner.readJSONFilesFromRessources("themes");
        if (jsonFiles != null) {
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

    /**
     * Select an theme randomly in theme array
     *
     * @return the theme
     */
    public String selectRandomTheme() {
        do {
            if (NB_OF_THEMES > 1) {
                int randomIndex = new Random().nextInt(NB_OF_THEMES);
                if (randomIndex != indicator) {
                    indicator = randomIndex;
                    try {
                        return this.titles.get(randomIndex);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else return this.titles.get(0);
        } while (true);
    }

    /**
     * Select N theme randomly without duplicate
     *
     * @param nbOfThemes the number of theme that we want ( < NB_OF_THEME )
     * @return array of themes
     */
    public String[] selectNRandomTheme(int nbOfThemes) {
        if (nbOfThemes > NB_OF_THEMES) {
            System.err.println("Error nb of theme is greater that the number of themes available = " + NB_OF_THEMES);
            return null;
        } else {
            String[] themes = new String[nbOfThemes];
            ArrayList<Integer> nbSelected = new ArrayList<>();
            int randomIndex;
            for (int i = 0; i < nbOfThemes; i++) {
                do {
                    randomIndex = new Random().nextInt(NB_OF_THEMES);
                } while (nbSelected.contains(randomIndex));
                indicator = randomIndex;
                nbSelected.add(randomIndex);
                themes[i] = this.titles.get(randomIndex);
            }
            return themes;
        }


    }

    /**
     * Select a theme different that the previous theme
     *
     * @return the theme
     */
    public String selectTheme() {
        if (indicator > 9) indicator = 0; // reset
        indicator++;
        return titles.get((indicator - 1));
    }


    public String[] selectFiveTheme() {
        return selectNRandomTheme(5);
    }


}
