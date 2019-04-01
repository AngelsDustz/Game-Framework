package group3.griddie.game.tictactoe;

import group3.griddie.controller.board.BoardController;
import group3.griddie.game.Game;
import group3.griddie.model.board.Board;

public class TicTacToe extends Game {

    public TicTacToe()
    {
        super("TicTacToe");

        new BoardController(board = new Board(3, 3));
    }

    @Override
    public void init() {
        System.out.println("Tic tac toe launched");
    }
}
