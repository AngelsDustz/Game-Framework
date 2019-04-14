package group3.griddie.game.reversi;

import group3.griddie.game.Game;
import group3.griddie.game.Move;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Board;

public class Reversi extends Game {

    public Reversi() {
        super("Reversi");
    }

    @Override
    public boolean moveIsValid(Player player, int x, int y) {
        return false;
    }

    @Override
    public boolean checkWin(Move move) {
        return false;
    }

    @Override
    protected void onPlayerMove(Player player, int column, int row) {

    }

    @Override
    protected Board createBoard() {
        return null;
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onTick() {

    }
}
