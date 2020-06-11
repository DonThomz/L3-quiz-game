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
    ArrayList<Player> playersConflict;

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

    public void resetSelect(Player player){
        this.players.forEach(player1 ->{
            if(player1.getId() == player.getId()){
                player1.changeStat(StatePlayer.WAITING);
            }
        });
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

        // Display Player with Timer
        System.out.println("Round finish / score and timer :");
        mapTimer.forEach((player, aLong) -> System.out.println(player + " timer = " + aLong));

        // Compare scores
        int minScores = Collections.min(mapTimer.keySet().stream().map(Player::getScore).collect(Collectors.toList()));
        List<Map.Entry<Player, Long>> playerList;
        playerList = mapTimer.entrySet().stream()
                .filter(playerLongEntry -> (playerLongEntry.getKey().getScore() == minScores)).collect(Collectors.toList());
        if (playerList.size() > 1) {
            // Compare timer
            Long maxTimers = Collections.max(playerList.stream().map(Map.Entry::getValue).collect(Collectors.toList()));
            playerList = playerList.stream().filter(playerLongEntry -> playerLongEntry.getValue().equals(maxTimers)).collect(Collectors.toList());
            if (playerList.size() > 1) {
                playersConflict = new ArrayList<>();
                playersConflict.addAll(playerList.stream().map(Map.Entry::getKey).collect(Collectors.toList()));
                return true;
            } else {
                // remove the player
                this.playersEliminated.add(playerList.get(0).getKey());
                this.players.remove(playerList.get(0).getKey());
                return false;
            }
        } else {
            // remove the player
            this.playersEliminated.add(playerList.get(0).getKey());
            this.players.remove(playerList.get(0).getKey());
            return false;
        }
    }

    public void removePlayerRandomly() {
        int ID = new Random().nextInt(this.getPlayers().size());
        // remove the player
        this.playersEliminated.add(this.getPlayers().get(ID));
        this.players.remove(ID);
    }

    /**
     * Get the list of players who need an extra round
     *
     * @return the list of players who need an extra round
     */
    public ArrayList<Player> getConflictPlayers() {
        return playersConflict;
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
