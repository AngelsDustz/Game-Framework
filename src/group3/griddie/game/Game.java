package group3.griddie.game;

import group3.griddie.controller.board.BoardController;
import group3.griddie.model.board.Board;
import javafx.scene.layout.BorderPane;

public abstract class Game {

    private String title;
    private BorderPane pane;
    private Board board;

    public Game(String title) {
        this.title = title;

        pane = new BorderPane();
    }

    public abstract void init();

    protected void setBoard(Board board) {
        this.board = board;
        pane.setCenter((new BoardController(board)).getView().getParent());
    }

    public String getTitle() {
        return title;
    }

    public BorderPane getPane() {
        return pane;
    }
}
