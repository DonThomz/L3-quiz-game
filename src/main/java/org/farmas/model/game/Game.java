package org.farmas.model.game;

import org.farmas.App;
import org.farmas.model.game.phase.Phase;
import org.farmas.model.game.phase.Phase1;
import org.farmas.model.game.phase.Phase2;
import org.farmas.model.players.Player;
import org.farmas.model.themes.Themes;

import java.util.*;
import java.util.stream.Collectors;

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

    public void runPhaseII() {
        phases[1] = new Phase2(players, themes);
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

    public boolean removePlayer(Map<Player, Long> mapTimer) {
        List<Integer> scores = this.players.stream().map(Player::getScore).collect(Collectors.toList());
        int minScore = Collections.min(scores);
        int conflict = (int) scores.stream().filter(score -> score == minScore).count();
        if (conflict > 1) {
            // compare timer
            long minTime = Collections.min(mapTimer.values());
            int conflictTimer = (int) mapTimer.values().stream().filter(delay -> delay == minTime).count();
            if (conflictTimer > 1) {
                // return false => need an conflit round
                return false;
            } else {
                for (Map.Entry<Player, Long> entry : mapTimer.entrySet()) {
                    if (Objects.equals(minTime, entry.getValue())) {
                        this.players.remove(entry.getKey());
                    }
                }
                return true;
            }
        } else {
            // remove the player
            this.players.removeIf(player -> player.getScore() == minScore);
            return true;
        }
    }

    public Themes getThemes() {
        return themes;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Phase[] getPhases() {
        return phases;
    }

    public Phase1 getPhaseI() {
        return (Phase1) phases[0];
    }

    public Phase2 getPhaseII() {
        return (Phase2) phases[1];
    }

}
