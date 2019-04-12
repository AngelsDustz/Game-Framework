package group3.griddie.game;

import group3.griddie.game.player.Player;
import group3.griddie.model.board.actor.Actor;

public class Lobby extends Entity {

    private int size;
    private Player[] players;
    private int count;
    private Game game;

    public Lobby(int size, Game game) {
        this.size = size;
        players = new Player[size];
        this.game = game;
    }

    public void join(Player player) {
        players[count] = player;
        count++;

        player.setName("Player " + count);
        player.setGame(game);
        player.setActorType(count == 1 ? Actor.Type.TYPE_1 : Actor.Type.TYPE_2); //TEMPORARILY FIX
        player.init();

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
