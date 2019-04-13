package group3.griddie.view.game;

import group3.griddie.controller.game.GameController;
import group3.griddie.controller.game.QuitController;
import group3.griddie.game.Game;
import group3.griddie.view.View;
import group3.griddie.view.game.board.BoardView;
import group3.griddie.view.game.sidebar.OpponentSelectView;
import group3.griddie.view.game.sidebar.QuitView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class GameView extends View {

    private Game game;

    private BoardView boardView;
    private VBox sidebar;

    public GameView(Game game) {
        super(new BorderPane(), new GameController());
        this.game = game;
        BorderPane root = (BorderPane) getRoot();
        boardView = new BoardView(game.getBoard());
        OpponentSelectView rightSidebar = new OpponentSelectView(this);
        Pane topPane = new Pane();
        QuitView quit = new QuitView(new Pane(), new QuitController());
        Pane bottomPane = new Pane();

        quit.setPrefSize(700, 0);
        topPane.setPrefSize(1980, 300);
        bottomPane.setPrefSize(1980, 300);
        rightSidebar.setPrefWidth(300);
        boardView.setPrefSize(500,500);

        BackgroundSize backgroundSize = new BackgroundSize(300, 300, true, true, true, false);
        BackgroundImage image = new BackgroundImage(new Image("/assets/images/top.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,null, backgroundSize);
        topPane.setBackground(new Background(image));
        image = new BackgroundImage(new Image("/assets/images/bottom.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,null, backgroundSize);
        bottomPane.setBackground(new Background(image));
        image = new BackgroundImage(new Image("/assets/images/middle.png"), null, null,null, null);
        boardView.setBackground(new Background(image));
        quit.setBackground(new Background(image));
        rightSidebar.setBackground(new Background(image));


        root.setBottom(bottomPane);
        root.setTop(topPane);
        root.setCenter(boardView);
        root.setLeft(quit);
        setSideBar(rightSidebar);
    }

    public void setSideBar(Node node) {
        BorderPane root = (BorderPane) getRoot();
        root.setRight(node);
    }

    public Game getGame() {
        return game;
    }
}
