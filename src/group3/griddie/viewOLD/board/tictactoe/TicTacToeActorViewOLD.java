package group3.griddie.viewOLD.board.tictactoe;

import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.viewOLD.board.ActorViewOLD;
import javafx.scene.image.Image;

public class TicTacToeActorViewOLD extends ActorViewOLD {

    public TicTacToeActorViewOLD(TicTacToeActor actor) {
        super(actor);
    }

    @Override
    protected Image getImage() {
        return new Image(
                ((TicTacToeActor) getModel()).getType() == Actor.Type.TYPE_1
                        ? "assets/images/o.png"
                        : "assets/images/x.png"
        );
    }
}
