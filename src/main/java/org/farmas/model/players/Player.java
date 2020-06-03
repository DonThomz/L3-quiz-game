package org.farmas.model.players;

public class Player implements Cloneable {

    private static int playersInGame = 0;
    private final int id;
    private final String name;
    private int score;
    private StatePlayer state;

    /**
     * Constructor Player
     * id player id start to 100 with step by 10
     *
     * @param name start with A
     *             By default state is WAITING and score = 0
     */
    public Player(String name) {
        this.id = playersInGame;
        playersInGame++;
        this.name = name;
        this.score = 0;
        this.state = StatePlayer.WAITING; // by default
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public StatePlayer getState() {
        return state;
    }

    /**
     * Update player score
     *
     * @param score score to be add
     */
    public void updateScore(int score, boolean isCorrect) {
        if(isCorrect) this.score += score;
        else this.score -= score;

    }

    /**
     * Change the state of the player
     *
     * @param state new StatePlayer
     */
    public void changeStat(StatePlayer state) {
        this.state = state;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
