package group3.griddie.view.game;

import group3.griddie.controller.game.GameController;
import group3.griddie.game.Game;
import group3.griddie.view.View;
import group3.griddie.view.game.board.BoardView;
import group3.griddie.view.game.sidebar.OpponentSelectView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameView extends View {

    private Game game;

    private BoardView boardView;
    private VBox sidebar;

    public GameView(Game game) {
        super(new BorderPane(), new GameController());

        this.game = game;

        BorderPane root = (BorderPane) getRoot();

        boardView = new BoardView(game.getBoard());

        root.setCenter(boardView);

        setSideBar(new OpponentSelectView(this));
    }

    public void setSideBar(Node node) {
        BorderPane root = (BorderPane) getRoot();
        root.setRight(node);
    }

    public Game getGame() {
        return game;
    }
}
