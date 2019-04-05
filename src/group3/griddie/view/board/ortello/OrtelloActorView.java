package group3.griddie.view.board.ortello;

import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.OrtelloActor;
import group3.griddie.view.board.ActorView;
import javafx.scene.image.Image;

public class OrtelloActorView extends ActorView {

    public OrtelloActorView(OrtelloActor actor) {
        super(actor);
    }

    @Override
    protected Image getImage() {
        OrtelloActor actor = (OrtelloActor) getModel();

        return new Image(
                actor.getType() == Actor.Type.TYPE_1
                        ? "assets/images/blackrevision.png"
                        : "assets/images/whiterevision.png"
        );
    }
}
