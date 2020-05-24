package org.farmas.model.game;

import org.farmas.App;
import org.farmas.model.game.phase.Phase;
import org.farmas.model.game.phase.Phase1;
import org.farmas.model.game.phase.Phase2;
import org.farmas.model.game.phase.Phase3;
import org.farmas.model.players.Player;
import org.farmas.model.players.PlayerSet;

import java.util.ArrayList;

public class Game {

    private static int MAX_PLAYERS = 4;

    Phase[] phases;
    ArrayList<Player> players;

    public Game() {
        phases = new Phase[]{new Phase1(), new Phase2(), new Phase3()};
        players = new ArrayList<>();

        // select 4 players
        pickPlayers();

    }

    private void pickPlayers() {
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

}
