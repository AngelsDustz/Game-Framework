package group3.griddie.view.game.board;

import group3.griddie.model.board.actor.Actor;
import group3.griddie.view.View;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class ActorView extends View {

    private ImageView imageView;

    public ActorView(Actor actor) {
        super(new StackPane(), null);

        imageView = new ImageView();
        imageView.setImage(getImage(actor));

        getChildren().add(imageView);
    }

    public abstract Image getImage(Actor actor);

}
