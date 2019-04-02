package group3.griddie.game;

import group3.griddie.controller.board.BoardController;
import group3.griddie.model.board.Board;
import javafx.scene.layout.BorderPane;

public abstract class Game {

    private String title;
    private Board board;
    private BorderPane pane;
    private BoardController controller;

    public Game(String title) {
        this.title = title;

        board = createBoard();
        BoardController boardController = new BoardController(board);

        pane = new BorderPane();
        pane.setCenter(boardController.getView().getNode());
    }

    public abstract Board createBoard();

    public abstract void init();

    public String getTitle() {
        return title;
    }

    public BorderPane getPane() {
        return pane;
    }
}
