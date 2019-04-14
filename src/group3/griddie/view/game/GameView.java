package group3.griddie.view.game;

import group3.griddie.controller.game.GameController;
import group3.griddie.controller.game.QuitController;
import group3.griddie.controller.menu.ChallengePlayerController;
import group3.griddie.game.Game;
import group3.griddie.game.othello.Othello;
import group3.griddie.game.tictactoe.TicTacToe;
import group3.griddie.view.View;
import group3.griddie.view.game.board.BoardView;
import group3.griddie.view.game.menu.ChallengePlayerView;
import group3.griddie.view.game.sidebar.OpponentSelectView;
import group3.griddie.view.game.sidebar.QuitView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class GameView extends View {

    private Game game;

    private BoardView boardView;
    private VBox sidebar;
    private BorderPane root;

    public GameView(Game game) {
        //init
        super(new StackPane(), new GameController());

        this.game = game;
        StackPane root_ = (StackPane) getRoot();
        VBox menu = new VBox();
        Pane topMenuPane = new Pane();
        Pane bottomMenuPane = new Pane();
        Text topText = new Text(10, 140, "SELECT OPPONENT");
        topMenuPane.getStyleClass().add("title");
        topMenuPane.getChildren().add(topText);
        bottomMenuPane.prefWidthProperty().bind(topMenuPane.widthProperty());
        bottomMenuPane.setPrefHeight(140);

        BorderPane root = new BorderPane();
        boardView = new BoardView(game.getBoard());
        VBox boardViewRoot = new VBox();
        GridPane ChallengePlayer = new GridPane();
        ChallengePlayerView challengePlayerView = new ChallengePlayerView(ChallengePlayer, new ChallengePlayerController(root_, ChallengePlayer), game);
        OpponentSelectView rightSidebar = new OpponentSelectView(this, root_, menu, (GridPane) challengePlayerView.getRoot());
        Pane topPane = new Pane();
        QuitView quit = new QuitView(new Pane(), new QuitController());
        Pane bottomPane = new Pane();

        //set sizes
        topPane.prefWidthProperty().bind(root.widthProperty());
        bottomPane.prefWidthProperty().bind(root.widthProperty());
        root_.prefWidthProperty().bind(root.widthProperty());
        root_.prefHeightProperty().bind(root.heightProperty());

        if (getGame() instanceof Othello) {
            topPane.setPrefHeight(200);
            bottomPane.setPrefHeight(300);
            rightSidebar.setPrefWidth(100);
            quit.setPrefWidth(100);
        }

        else if(getGame() instanceof TicTacToe){
            topPane.setPrefHeight(400);
            bottomPane.setPrefHeight(425);
            rightSidebar.setPrefWidth(200);
            quit.setPrefWidth(400);
        }

        //setup background
        BackgroundSize backgroundSize = new BackgroundSize(300, 300, true, true, true, false);
        BackgroundImage image = new BackgroundImage(new Image("/assets/images/top.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,null, backgroundSize);
        topPane.setBackground(new Background(image));
        topMenuPane.setBackground(new Background(image));
        image = new BackgroundImage(new Image("/assets/images/bottom.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,null, backgroundSize);
        bottomPane.setBackground(new Background(image));
        bottomMenuPane.setBackground(new Background(image));
        image = new BackgroundImage(new Image("/assets/images/middle.png"), null, null,null, null);
        boardView.setBackground(new Background(image));
        quit.setBackground(new Background(image));
        rightSidebar.setBackground(new Background(image));
        boardViewRoot.setBackground(new Background(image));

        //add everything to root
        boardViewRoot.getChildren().add(boardView);
        boardViewRoot.setAlignment(Pos.CENTER);
        root.setCenter(boardViewRoot);
        BorderPane.setAlignment(boardViewRoot, Pos.CENTER);
        root.setBottom(bottomPane);
        root.setTop(topPane);
        root.setLeft(quit);
        menu.getChildren().addAll(topMenuPane, rightSidebar, bottomMenuPane);
        root_.getChildren().add(root);
        root_.getChildren().add(menu);
    }

    public void setSideBar(Node node) {
        BorderPane root = (BorderPane) getRoot();
        root.setRight(node);
    }

    public Game getGame() {
        return game;
    }
}
