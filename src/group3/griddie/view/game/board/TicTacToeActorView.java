package group3.griddie.view.game.board;

import group3.griddie.model.board.actor.Actor;
import javafx.scene.image.Image;

public class TicTacToeActorView extends ActorView {
    public TicTacToeActorView(Actor actor) {
        super(actor);
    }

    @Override
    public Image getImage(Actor actor) {
        return new Image(actor.getType() == Actor.Type.TYPE_1 ? "assets/images/x.png" : "assets/images/o.png");
    }
}
