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
    private static final int WIDTH  = 64;
    private static final int HEIGHT = 64;

    private ActorViewOLD actorView;
    private ImageView imageView;

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

            if (actorView.getModel() != occupant) {
                actorView.setModel(occupant);
            }

            if (((Cell) o).isValidSpot()) {
                this.imageView.setImage(new Image("assets/images/node_valid.png"));
            } else {
                this.imageView.setImage(new Image("assets/images/node.png"));
            }
        }
    }

    @Override
    public void initializeView() {
        StackPane stackPane = (StackPane) getNode();
        this.imageView = new ImageView(new Image("assets/images/node.png"));

        stackPane.getChildren().add(this.imageView);
        stackPane.getChildren().add(actorView.getNode());
    }

    @Override
    public void initializeControls() {
        CellController controller = (CellController) getController();
        getNode().setOnMouseClicked(event -> controller.onClick());
    }
}
