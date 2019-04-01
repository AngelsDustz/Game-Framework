package group3.griddie.game;

import group3.griddie.model.board.Board;

public abstract class Game {

    private Board board;

    public Game() {

    }

    protected void init(int width, int height) {
        this.board = new Board(width, height);
    }

}
