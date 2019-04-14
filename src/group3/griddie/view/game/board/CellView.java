package group3.griddie.view.game.board;

import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.OthelloActor;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.view.View;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Observable;
import java.util.Observer;

public class CellView extends View implements Observer {
    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;

    private Canvas canvas;
    private ImageView imageView;
    private ActorView actorView;

    public CellView(Cell cell) {
        super(new StackPane(), null);

        StackPane root = (StackPane) getRoot();

        canvas      = new Canvas();
        imageView   = new ImageView();

        cell.addObserver(this);

        root.getChildren().add(canvas);
        root.getChildren().add(imageView);

        setOnMouseClicked(event -> {
            cell.interact();
        });
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Cell) {
            Platform.runLater(() -> {
                if (actorView != null) {
                    getChildren().remove(actorView);
                }

                Actor actor = ((Cell) o).getOccupant();

                if (((Cell) o).isValidSpot()) {
                    this.setImage(new Image("assets/images/node_valid.png"));
                } else {
                    this.setImage(new Image("assets/images/node.png"));
                }

                if (actor instanceof OthelloActor) {
                    getChildren().add(actorView = new OthelloActorView(actor));
                }

                if (actor instanceof TicTacToeActor) {
                    getChildren().add(actorView = new TicTacToeActorView(actor));
                }
            });
        }
    }

}
