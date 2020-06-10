package org.farmas.model.game;

import org.farmas.App;
import org.farmas.model.game.phase.*;
import org.farmas.model.players.Player;
import org.farmas.model.players.StatePlayer;
import org.farmas.model.themes.Themes;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    public static final int MAX_PLAYERS = 4;

    Phase[] phases;
    ExtraPhase extraPhase;
    Themes themes;
    ArrayList<Player> players;
    ArrayList<Player> playersEliminated;

    public Game() {
        phases = new Phase[3];
        players = new ArrayList<>();
        playersEliminated = new ArrayList<>();
        // init themes
        this.themes = new Themes();

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

    public void runExtraPhase(ArrayList<Player> conflictPlayers, int ROUND) {
        extraPhase = new ExtraPhase(conflictPlayers, ROUND, themes);
    }

    /*
        Select MAX_PLAYERS players and put it in players arrayList
     */
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

    /**
     * Remove the last player during a round
     *
     * @param mapTimer stopwatch of players
     * @return true if no conflict and remove the player else return false to indicate the need of an extra round
     */
    public boolean removePlayer(Map<Player, Long> mapTimer) {
        List<Integer> scores = this.players.stream().map(Player::getScore).collect(Collectors.toList());
        int minScore = Collections.min(scores);
        int conflict = (int) scores.stream().filter(score -> score == minScore).count();
        if (conflict > 1) {
            // compare timer
            long minTime = Collections.min(mapTimer.values());
            int conflictTimer = (int) mapTimer.values().stream().filter(delay -> delay == minTime).count();
            if (conflictTimer > 1) {
                // return true => need an conflict round
                return true;
            } else {
                for (Map.Entry<Player, Long> entry : mapTimer.entrySet()) {
                    if (Objects.equals(minTime, entry.getValue())) {
                        this.playersEliminated.add(entry.getKey());
                        this.players.remove(entry.getKey());
                        // change stat
                        this.playersEliminated.get(this.playersEliminated.size() - 1).changeStat(StatePlayer.EXCLUDE);
                    }
                }
                return false;
            }
        } else {
            // remove the player
            this.playersEliminated.add(players.stream().filter(player -> player.getScore() == minScore).collect(Collectors.toList()).get(0));
            this.players.removeIf(player -> player.getScore() == minScore);
            return false;
        }
    }

    /**
     * Get the list of players who need an extra round
     *
     * @param mapTimer stopwatch of players
     * @return the list of players who need an extra round
     */
    public ArrayList<Player> getConflictPlayers(Map<Player, Long> mapTimer) {
        int minScore = Collections.min(mapTimer.keySet().stream().map(Player::getScore).collect(Collectors.toList()));
        long minTime = Collections.min(mapTimer.values());
        ArrayList<Player> conflictPlayers = (ArrayList<Player>) mapTimer.entrySet().stream()
                .filter((player) -> player.getValue() == minTime && player.getKey().getScore() == minScore)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (conflictPlayers.size() > 0) return conflictPlayers;
        else return null;
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

    public ExtraPhase getExtraPhase() {
        return extraPhase;
    }
}
