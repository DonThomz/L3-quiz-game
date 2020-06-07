package org.farmas.model.game;

import org.farmas.App;
import org.farmas.model.game.phase.Phase;
import org.farmas.model.game.phase.Phase1;
import org.farmas.model.game.phase.Phase2;
import org.farmas.model.game.phase.Phase3;
import org.farmas.model.players.Player;
import org.farmas.model.themes.Themes;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    public static final int MAX_PLAYERS = 4;

    Phase[] phases;
    Themes themes;
    ArrayList<Player> players;
    ArrayList<Player> playersEliminated;

    public Game() {
        phases = new Phase[3];
        players = new ArrayList<>();
        playersEliminated = new ArrayList<>();
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

    public void runPhaseIII() {
        phases[2] = new Phase3(players, themes);
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
                return true;
            } else {
                for (Map.Entry<Player, Long> entry : mapTimer.entrySet()) {
                    if (Objects.equals(minTime, entry.getValue())) {
                        this.playersEliminated.add(entry.getKey());
                        this.players.remove(entry.getKey());
                    }
                }
                return false;
            }
        } else {
            // remove the player
            this.players.removeIf(player -> player.getScore() == minScore);
            return false;
        }
    }

    public Themes getThemes() {
        return themes;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getPlayersEliminated() {
        return playersEliminated;
    }

    public Phase[] getPhases() {
        return phases;
    }

    public Phase1 getPhaseI() {
        return phases[0] != null ? (Phase1) phases[0] : null;
    }

    public Phase2 getPhaseII() {
        return phases[1] != null ? (Phase2) phases[1] : null;
    }

    public Phase3 getPhaseIII() {
        return phases[2] != null ? (Phase3) phases[2] : null;
    }

}
