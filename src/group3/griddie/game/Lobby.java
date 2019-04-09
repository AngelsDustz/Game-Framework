package group3.griddie.game;

import group3.griddie.game.player.Player;

import java.util.Observable;

public class Lobby extends Observable {

    private int size;
    private Player[] players;
    private int count;

    public Lobby(int size) {
        this.size = size;
        players = new Player[size];
    }

    public void join(Player player) {
        players[count] = player;
        count++;

        setChanged();
        notifyObservers();
    }

    public boolean isFull() {
        return count == size;
    }

    public Player[] getPlayers() {
        return players.clone();
    }

    public Player getPlayer(int index) {
        return players[index];
    }

    public int getSize() {
        return size;
    }
}
