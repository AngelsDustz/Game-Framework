package group3.griddie.view.game.sidebar;

import group3.griddie.controller.Controller;
import group3.griddie.game.Game;
import group3.griddie.game.othello.Othello;
import group3.griddie.game.player.Player;
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

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;


public class RightBarGameView extends View implements Observer {
    private Othello game;
    private Label scoreText;
    private Label scoreText2;

    public RightBarGameView(Parent root, Controller controller, Game game, StackPane parent) {
        super(root, controller);
        this.game = (Othello) game;

        game.onStart.addListener(() -> {
            for (Player player : game.getLobby().getPlayers()) {
                player.addObserver(this);
            }
        });

        VBox root_ = (VBox) getRoot();
        StackPane rootScoreBoard = new StackPane();
        StackPane rootScoreBoard2 = new StackPane();

        Image image = new Image("/assets/images/scoreboard.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        ImageView imageView2 = new ImageView();
        imageView2.setImage(image);

        scoreText = new Label("score");
        scoreText.getStyleClass().add("text-button-game-right");
        scoreText2 = new Label("score");
        scoreText2.getStyleClass().add("text-button-game-right");
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(new Runnable() {
//                    String name1;
//                    String name2;
//                    int start = 0;
//
//                    @Override
//                    public void run() {
//                        if (game.isRunning()) {
//                            if(start == 0 && game.getActivePlayer().getName() == null){
//                                String name1 = game.getActivePlayer().getName();
//                                String name2 = game.getNextPlayer().getName();
//                                start++;
//                            }
//
//                            scoreText.textProperty().setValue(String.valueOf("chinese blood sausage" + ": " + ((Othello) game).getCountByActor(game.getBoard(), Actor.Type.TYPE_1)));
//                            scoreText2.textProperty().setValue(String.valueOf("shin" + ": " + ((Othello) game).getCountByActor(game.getBoard(), Actor.Type.TYPE_2)));
//                            if (game.getActivePlayer().getActorType() == Actor.Type.TYPE_1) {
//                                scoreText.setTextFill(Color.YELLOW);
//                                scoreText2.setTextFill(Color.BLACK);
//                            } else if (game.getActivePlayer().getActorType() == Actor.Type.TYPE_2) {
//                                scoreText.setTextFill(Color.BLACK);
//                                scoreText2.setTextFill(Color.YELLOW);
//                            }
//                        }
//                    }
//                });
//            }
//        }, 0, 1000);

        BackgroundImage imagebg = new BackgroundImage(new Image("/assets/images/middle.png"), null, null, null, null);
        root_.setBackground(new Background(imagebg));
        rootScoreBoard.getChildren().addAll(imageView, scoreText);
        rootScoreBoard2.getChildren().addAll(imageView2, scoreText2);

        root_.getChildren().addAll(rootScoreBoard, rootScoreBoard2);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Player) {
            System.out.println("Text updated");
            Platform.runLater(() -> {
                if (((Player) o).getActorType() == Actor.Type.TYPE_1) {
                    scoreText.textProperty().setValue(String.valueOf(((Player) o).getName() + ": " + ((Othello) game).getCountByActor(game.getBoard(), Actor.Type.TYPE_1)));
                    scoreText.setTextFill(Color.YELLOW);
                    scoreText2.setTextFill(Color.BLACK);
                } else if (((Player) o).getActorType() == Actor.Type.TYPE_2) {
                    scoreText2.textProperty().setValue(String.valueOf(((Player) o).getName() + ": " + ((Othello) game).getCountByActor(game.getBoard(), Actor.Type.TYPE_2)));
                    scoreText.setTextFill(Color.BLACK);
                    scoreText2.setTextFill(Color.YELLOW);
                }
            });
        }
    }
}
