package group3.griddie.game;

import group3.griddie.model.board.Board;

public abstract class Game {

    protected Board board;

    private String title;

    public Game(String title) {
        this.title = title;
    }

    public abstract void init();

    public String getTitle() {
        return title;
    }
}
