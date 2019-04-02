package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.model.board.Board;

public class TicTacToe extends Game {
    private Board board;

    public TicTacToe() {
        super("Tic Tac Toe");
    }

    @Override
    public void init() {
        if (this.board == null) {
            this.board = new Board(3, 3, Board.Pattern.NONE);
        }

        setBoard(this.board);
    }
}
