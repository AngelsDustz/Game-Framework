package group3.griddie.view.game.board;

import group3.griddie.model.board.actor.Actor;
import javafx.scene.image.Image;

public class OthelloActorView extends ActorView {

    public OthelloActorView(Actor actor) {
        super(actor);
    }

    @Override
    public Image getImage(Actor actor) {
        return new Image(actor.getType() == Actor.Type.TYPE_1 ? "assets/images/blackrevision.png" : "assets/images/whiterevision.png");
    }

}
