package group3.griddie.view.board.tictactoe;

import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.view.board.ActorView;
import javafx.scene.image.Image;

public class TicTacToeActorView extends ActorView {

    public TicTacToeActorView(TicTacToeActor actor) {
        super(actor);
    }

    @Override
    protected Image getImage() {
        return new Image(
                ((TicTacToeActor) getModel()).getType() == TicTacToeActor.Type.O
                        ? "assets/images/o.png"
                        : "assets/images/x.png"
        );
    }
}
