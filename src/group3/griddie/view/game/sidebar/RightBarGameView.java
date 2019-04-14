package group3.griddie.view.game.sidebar;

import group3.griddie.controller.Controller;
import group3.griddie.game.Game;
import group3.griddie.game.othello.Othello;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.view.View;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;


public class RightBarGameView extends View {
    private Othello game;

    public RightBarGameView(Parent root, Controller controller, Game game, StackPane parent) {
        super(root, controller);
        this.game = (Othello) game;

        VBox root_ = (VBox) getRoot();
        StackPane rootScoreBoard = new StackPane();
        StackPane rootScoreBoard2 = new StackPane();

        Image image = new Image("/assets/images/scoreboard.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        ImageView imageView2 = new ImageView();
        imageView2.setImage(image);

        Label scoreText = new Label("score");
        scoreText.getStyleClass().add("text-button-game-right");
        Label scoreText2 = new Label("score");
        scoreText2.getStyleClass().add("text-button-game-right");

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scoreText.textProperty().setValue(String.valueOf("Player 1: " + ((Othello) game).getCountByActor(game.getBoard(), Actor.Type.TYPE_1)));
                        scoreText2.textProperty().setValue(String.valueOf("Player 2: " + ((Othello) game).getCountByActor(game.getBoard(), Actor.Type.TYPE_2)));
                        if (game.isRunning()) {
                            if (game.getActivePlayer().getActorType() == Actor.Type.TYPE_1) {
                                scoreText.setTextFill(Color.YELLOW);
                                scoreText2.setTextFill(Color.BLACK);
                            } else if (game.getActivePlayer().getActorType() == Actor.Type.TYPE_2) {
                                scoreText.setTextFill(Color.BLACK);
                                scoreText2.setTextFill(Color.YELLOW);
                            }
                        }
                    }
                });
            }
        }, 0, 1);

        BackgroundImage imagebg = new BackgroundImage(new Image("/assets/images/middle.png"), null, null,null, null);
        root_.setBackground(new Background(imagebg));
        rootScoreBoard.getChildren().addAll(imageView,scoreText);
        rootScoreBoard2.getChildren().addAll(imageView2, scoreText2);

        root_.getChildren().addAll(rootScoreBoard, rootScoreBoard2);
    }


}
