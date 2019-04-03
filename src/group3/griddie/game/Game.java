package group3.griddie.game;

import group3.griddie.controller.board.BoardController;
import group3.griddie.model.board.Board;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public abstract class Game extends Scene {

    private Board board;
    private boolean started;
    private int turn;

    public Game() {
        super(new BorderPane());
    }

    public final void init() {
        board = createBoard();

        BorderPane root = (BorderPane) getRoot();
        root.setCenter(new BoardController(board).getView().getNode());

        onInit();
    }

    public final void start() {
        started = true;

        onStart();
    }

    public final void stop() {
        started = false;

        onStop();
    }

    public final void tick() {
        turn++;

        onTick();
    }

    protected abstract Board createBoard();
    protected abstract void onInit();
    protected abstract void onStart();
    protected abstract void onStop();
    protected abstract void onTick();

}
