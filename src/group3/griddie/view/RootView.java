package group3.griddie.view;

import group3.griddie.model.Model;
import javafx.scene.Parent;

public abstract class RootView<M extends Model> extends View<M> {

    public RootView(Model model, Parent parent) {
        super(model, parent);
    }

    public Parent getParent() {
        return (Parent) getNode();
    }

}
