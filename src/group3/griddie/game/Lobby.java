package group3.griddie.game;

import group3.griddie.game.player.Player;

public class Lobby extends Entity {

    private int size;
    private Player[] players;
    private int count;
    private Game game;

    public Lobby(int size, Game game) {
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

    public Game getGame() {
        return game;
    }
}
