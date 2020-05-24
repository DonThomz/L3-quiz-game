package org.farmas.model.themes;

import java.util.ArrayList;

public class Themes {

    private String[] themes;
    private int indicator;

    public Themes() {
        // TODO charger les thèmes
        this.themes = new String[10];
        this.indicator = -1;
    }

    public String[] getArrayOfThemes() {
        return themes;
    }

    public int getIndicator() {
        return indicator;
    }

    public String getCurrentTheme(){
        return this.getArrayOfThemes()[this.getIndicator()];
    }

    public void modifyTheme(String theme, int indexTheme){
        this.getArrayOfThemes()[indexTheme] = theme;
    }
    
    public String selectTheme(){
        do {
            int randomIndex = (int) Math.round(Math.random() * themes.length + 1);
            if(randomIndex != this.indicator){
                this.indicator = randomIndex;
                return this.themes[randomIndex];
            }
        }while(true);
    }

    public String[] selectFiveTheme(){
        String[] themes = new String[5];
        ArrayList<Integer> randomNbPick = new ArrayList<>();
        int randomNumber;
        for (int i = 0; i < 5; i++) {
            // pick a random number
            do{
                randomNumber = (int) (Math.random() * 10 + 1);
            }while(randomNbPick.contains(randomNumber)); // check if previously taken
            randomNbPick.add(randomNumber);
            themes[i] = this.themes[randomNumber];
        }
        return themes;
    }

}
