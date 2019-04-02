package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.model.board.Board;

public class TicTacToe extends Game {

    private Board board;

    public TicTacToe() {
        super("Tic Tac Toe");
    }

    @Override
    public Board createBoard() {
        return new Board(3, 3, Board.Pattern.NONE);
    }

    @Override
    public void init() {

    }

}
