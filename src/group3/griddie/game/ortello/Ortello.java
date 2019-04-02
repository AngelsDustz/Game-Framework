package group3.griddie.game.ortello;

import group3.griddie.game.Game;
import group3.griddie.model.board.Board;

public class Ortello extends Game {

    public Ortello() {
        super("Ortello");
    }

    @Override
    public void init() {
        setBoard(new Board(10, 10, Board.Pattern.CHECKER));
    }
}
