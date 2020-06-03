package org.farmas.model.game;

import org.farmas.App;
import org.farmas.model.game.phase.Phase;
import org.farmas.model.game.phase.Phase1;
import org.farmas.model.players.Player;
import org.farmas.model.themes.Themes;

import java.util.ArrayList;

public class Game {

    private static final int MAX_PLAYERS = 4;

    Phase[] phases;
    Themes themes;
    ArrayList<Player> players;

    public Game() {
        phases = new Phase[3];
        players = new ArrayList<>();

        // init themes
        this.themes = new Themes();

        // select 4 players
        //pickPlayers();

        //start();
    }


    public void start() {
        runPhaseI();
    }

    public void runPhaseI() {
        phases[0] = new Phase1(players, themes);
    }

    public void pickPlayers() {
        ArrayList<Integer> randomNbPick = new ArrayList<>();
        for (int i = 0; i < MAX_PLAYERS; i++) {
            Player tmp_p;
            do {
                tmp_p = App.playerSet.selectPlayer();

            } while (randomNbPick.contains(tmp_p.getId()));
            randomNbPick.add(tmp_p.getId());
            players.add(tmp_p);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Phase[] getPhases() {
        return phases;
    }

    public Phase1 getPhaseI(){
        return (Phase1) phases[0];
    }
}
