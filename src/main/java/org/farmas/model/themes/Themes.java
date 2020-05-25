package org.farmas.model.themes;

import java.util.ArrayList;
import java.util.Random;

public class Themes {

    private static final int NB_OF_THEMES = 1;
    private String[] themes;
    private int indicator;

    public Themes() {
        // TODO charger les thèmes
        this.themes = new String[]{"Mythology"};
        this.indicator = -1;
    }

    public String[] getArrayOfThemes() {
        return themes;
    }

    public int getIndicator() {
        return indicator;
    }

    public String getCurrentTheme() {
        return this.getArrayOfThemes()[this.getIndicator()];
    }

    public void modifyTheme(String theme, int indexTheme) {
        this.getArrayOfThemes()[indexTheme] = theme;
    }

    public String selectTheme() {
        do {
            if(NB_OF_THEMES > 1) {
                int randomIndex = new Random().nextInt(NB_OF_THEMES);
                System.out.println(randomIndex);
                if (randomIndex != this.indicator) {
                    this.indicator = randomIndex;
                    try {
                        return this.themes[randomIndex];
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }else return this.themes[0];
        }while(true);
    }

    public String[] selectFiveTheme() {
        String[] themes = new String[5];
        ArrayList<Integer> randomNbPick = new ArrayList<>();
        int randomNumber;
        for (int i = 0; i < 5; i++) {
            // pick a random number
            do{
                randomNumber = new Random().nextInt(NB_OF_THEMES);
            }while(randomNbPick.contains(randomNumber)); // check if previously taken
            randomNbPick.add(randomNumber);
            themes[i] = this.themes[randomNumber];
        }
        return themes;
    }

}
