package group3.griddie.viewOLD.board;

import group3.griddie.controllerOLD.board.CellController;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.viewOLD.ViewOLD;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Observable;
import java.util.Observer;

public class CellViewOLD extends ViewOLD<Cell> implements Observer {

    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;

    private ActorViewOLD actorView;

    public CellViewOLD(Cell cell, ActorViewOLD actorView) {
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
        CellController controller = (CellController) getController();
        getNode().setOnMouseClicked(event -> controller.onClick());
    }
}
