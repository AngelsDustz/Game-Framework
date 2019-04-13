package group3.griddie.game;

import group3.griddie.game.player.Player;

public class Move {

    private Player player;
    private int x;
    private int y;

    public Move(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public Player getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
