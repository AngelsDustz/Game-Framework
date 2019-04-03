package group3.griddie.view.board;

import group3.griddie.controller.board.CellController;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
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

    private ActorView actorView;

    public CellView(Cell cell, ActorView actorView) {
        super(cell, new StackPane());

        cell.addObserver(this);

        this.actorView = actorView;
    }

    @Override
    public void update(Observable o, Object arg) {
        StackPane root = (StackPane) getNode();

        if (o instanceof Cell) {
            Actor occupant = ((Cell) o).getOccupant();

            actorView.setModel(occupant);
        }
    }

    @Override
    public void initializeView() {
        StackPane stackPane = (StackPane) getNode();

        stackPane.getChildren().add(new ImageView(new Image("assets/images/node.png")));
        stackPane.getChildren().add(actorView.getNode());
    }

    @Override
    public void initializeControls() {
        getNode().setOnMouseClicked(event -> getController().onClick());
    }
}
