package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.model.board.Board;

public class TicTacToe extends Game {

    public TicTacToe()
    {
        super("TicTacToe");
        board = new Board(3, 3);
    }

    @Override
    public void init() {

    }
}
