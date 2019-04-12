package group3.griddie.viewOLD;

import group3.griddie.model.Model;
import javafx.scene.Parent;

public abstract class RootViewOLD<M extends Model> extends ViewOLD<M> {

    public RootViewOLD(Model model, Parent parent) {
        super(model, parent);
    }

    public Parent getParent() {
        return (Parent) getNode();
    }

}
