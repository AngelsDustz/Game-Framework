package group3.griddie.game;

import group3.griddie.game.player.Player;
import group3.griddie.model.board.actor.Actor;

import java.util.Observable;
import java.util.Observer;

public class Lobby extends Entity implements Observer {

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

        player.setGame(game);
        player.setActorType(count == 1 ? Actor.Type.TYPE_1 : Actor.Type.TYPE_2); //TEMPORARILY FIX
        player.init();

        setChanged();
        notifyObservers();

        player.addObserver(this);
    }

    public boolean allReady() {
        for (Player p : players) {
            if (p == null || !p.isReady())
                return false;
        }

        return true;
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

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Player) {
            this.setChanged();
            this.notifyObservers();
        }
    }
}
