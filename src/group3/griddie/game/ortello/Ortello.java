package group3.griddie.game.ortello;

import group3.griddie.game.Game;
import group3.griddie.model.board.Board;

public class Ortello extends Game {

    public Ortello() {
        super("Ortello");
    }

    @Override
    public Board createBoard() {
        return new Board(8, 8);
    }

    @Override
    public void init() {

    }
}
