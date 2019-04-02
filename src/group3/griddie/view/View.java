package group3.griddie.view;

import group3.griddie.controller.Controller;
import group3.griddie.model.Model;
import javafx.scene.Parent;

public abstract class View {

    private Controller controller;
    private Model model;
    private Parent parent;

    public View(Controller controller, Model model, Parent parent) {
        this.controller = controller;
        this.model = model;
        this.parent = parent;
    }

    protected Controller getController() {
        return controller;
    }

    public Model getModel() {
        return model;
    }

    public Parent getParent() {
        return parent;
    }

    public abstract void initializeView();

    public abstract void initializeControls();
}
