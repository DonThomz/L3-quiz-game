package org.farmas.model.players;

import java.util.Iterator;
import java.util.Vector;

public class PlayerSet implements Iterable<Player>{

    private Vector<Player> players;
    private final int numberOfPlayers;

    /**
     * Create player set with a custom size of players
     * @param numberOfPlayers pool size of players
     */
    PlayerSet(int numberOfPlayers){
        this.players = new Vector<>();
        this.numberOfPlayers = numberOfPlayers;
        this.build(); // initialization of players
    }

    /**
     * Create player set with a custom players vector.
     * Deep copy assured
     * @param players vector of players
     */
    PlayerSet(Vector<Player> players){
        this.numberOfPlayers = players.size();
        this.players = new Vector<>();
        for (Player player: players
        ) {
            try {
                this.players.add((Player) player.clone());
            }catch (CloneNotSupportedException cloneNotSupportedException){
                System.out.println("Error cloning player");
                cloneNotSupportedException.printStackTrace();
            }
        }
    }

    private void build(){
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players.add(new Player(Character.toString('A'+i)));
        }
    }

    public Vector<Player> getPlayers() {
        return players;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public Iterator<Player> iterator() {
        return this.getPlayers().iterator();
    }

    public Player selectPlayer(){
        int randomId = (int) Math.round((Math.random() * numberOfPlayers + 1));
        return this.getPlayers().get(randomId);
    }

}
