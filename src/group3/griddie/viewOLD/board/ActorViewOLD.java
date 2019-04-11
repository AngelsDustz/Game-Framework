package group3.griddie.viewOLD.board;

import group3.griddie.model.board.actor.Actor;
import group3.griddie.viewOLD.ViewOLD;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ActorViewOLD extends ViewOLD<Actor> {

    public ActorViewOLD(Actor model) {
        super(model, new ImageView());
    }

    @Override
    protected void initializeView() {
        if (getModel() == null) {
            return;
        }

        ImageView imageView = (ImageView) getNode();
        imageView.setImage(getImage());
    }

    @Override
    protected void initializeControls() {

    }

    protected abstract Image getImage();

}
