package group3.griddie.view.board;

import group3.griddie.controller.board.CellController;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.view.View;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class CellView extends View<CellController> implements Observer {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    public CellView(Cell cell) {
        super(cell, new Canvas(WIDTH, HEIGHT));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Cell) {
            Actor occupant = ((Cell) o).getOccupant();

            //TODO
        }
    }

    @Override
    public void initializeView() {
        Canvas canvas = (Canvas) getNode();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void initializeControls() {
        Canvas canvas = (Canvas) getNode();
        canvas.setOnMouseClicked(event -> getController().onClick());
    }
}
