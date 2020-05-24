package org.farmas.model.themes;

public class Themes {

    private String[] arrayOfThemes;
    private int indicator;

    public Themes() {
        // TODO charger les thèmes
        this.arrayOfThemes = new String[10];
        this.indicator = -1;
    }

    public String[] getArrayOfThemes() {
        return arrayOfThemes;
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
            int randomIndex = (int) Math.round(Math.random() * arrayOfThemes.length + 1);
            if(randomIndex != this.indicator){
                this.indicator = randomIndex;
                return this.arrayOfThemes[randomIndex];
            }
        }while(true);
    }
    

}
