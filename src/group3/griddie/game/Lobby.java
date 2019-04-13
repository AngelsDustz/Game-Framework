package group3.griddie.game;

import group3.griddie.game.player.Player;
import group3.griddie.util.event.ArgEvent;
import group3.griddie.util.event.Event;

public class Lobby extends Entity  {

    public final ArgEvent<Player> playerJoinedEvent;
    public final Event allPlayersReadyEvent;

    private int size;
    private int count;
    private Player[] players;

    public Lobby(int size) {
        this.size = size;
        players = new Player[size];

        playerJoinedEvent = new ArgEvent<>();
        allPlayersReadyEvent = new Event();
    }

    public void join(Player player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = player;
                count++;
                break;
            }
        }

        playerJoinedEvent.call(player);
    }


    public void kick(Player player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == player) {
                players[i] = null;
                count--;
                System.out.println("Kicked player " + player.getName());
            }
        }
    }

    public Player getPlayer(String name) {
        for (Player player : players) {
            if (player != null && player.getName().equals(name)) {
                return player;
            }
        }

        return null;
    }

    public Player getPlayer(int index) {
        return players[index];
    }

    public Player[] getPlayers() {
        return players.clone();
    }

    public boolean isFull() {
        return count == size;
    }

}
