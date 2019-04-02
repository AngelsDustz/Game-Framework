package group3.griddie.view;

import group3.griddie.controller.Controller;
import group3.griddie.model.Model;
import group3.griddie.view.View;
import javafx.scene.Parent;

public abstract class RootView<T extends Controller> extends View<T> {

    public RootView(Model model, Parent parent) {
        super(model, parent);
    }

    public Parent getParent() {
        return (Parent) getNode();
    }

}
