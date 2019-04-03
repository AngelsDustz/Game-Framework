package group3.griddie.view.board;

import group3.griddie.controller.board.CellController;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.view.View;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class CellView extends View<CellController> implements Observer {

    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;

    private Canvas canvas;
    private ImageView imageView;

    public CellView(Cell cell) {
        super(cell, new StackPane());

        cell.addObserver(this);

        canvas = new Canvas(WIDTH, HEIGHT);
        imageView = new ImageView();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Cell) {
            Actor occupant = ((Cell) o).getOccupant();

            drawOccupant(occupant);
        }
    }

    private void drawOccupant(Actor occupant) {
        if (occupant instanceof TicTacToeActor) {
            Image image = null;

            switch (((TicTacToeActor) occupant).getType()) {
                case O:
                    image = new Image("assets/images/o.png");
                    break;

                case X:
                    image = new Image("assets/images/x.png");
                    break;
            }

            imageView.setImage(image);
        }
    }

    @Override
    public void initializeView() {
        StackPane stackPane = (StackPane) getNode();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        stackPane.getChildren().add(canvas);
        stackPane.getChildren().add(imageView);
    }

    @Override
    public void initializeControls() {
        getNode().setOnMouseClicked(event -> getController().onClick());
    }
}
