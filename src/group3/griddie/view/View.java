package group3.griddie.view;

import group3.griddie.model.Model;
import javafx.scene.Parent;

public abstract class View {

    private Model model;
    private Parent parent;

    public View(Model model, Parent parent) {
        this.model = model;
        this.parent = parent;
    }

    public Parent getParent() {
        return parent;
    }

}
