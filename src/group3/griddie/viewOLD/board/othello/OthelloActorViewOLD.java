package group3.griddie.viewOLD.board.othello;

import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.OthelloActor;
import group3.griddie.viewOLD.board.ActorViewOLD;
import javafx.scene.image.Image;

public class OthelloActorViewOLD extends ActorViewOLD {

    public OthelloActorViewOLD(OthelloActor actor) {
        super(actor);
    }

    @Override
    protected Image getImage() {
        OthelloActor actor = (OthelloActor) getModel();

        return new Image(
                actor.getType() == Actor.Type.TYPE_1
                        ? "assets/images/blackrevision.png"
                        : "assets/images/whiterevision.png"
        );
    }
}
