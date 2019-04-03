package group3.griddie.view.board.ortello;

import group3.griddie.model.board.actor.OrtelloActor;
import group3.griddie.view.board.ActorView;
import javafx.scene.image.Image;

public class OrtelloActorView extends ActorView {

    public OrtelloActorView(OrtelloActor actor) {
        super(actor);
    }

    @Override
    protected Image getImage() {
        return new Image("assets/images/o.png");
    }
}
