package group3.griddie.view;

import group3.griddie.controller.Controller;
import group3.griddie.model.Model;
import javafx.scene.Parent;

public abstract class View<C extends Controller> {
    private C controller;
    private Model model;
    private Parent parent;

    public View(Model model, Parent parent) {
        this.model = model;
        this.parent = parent;
    }

    public C getController() {
        return controller;
    }

    public Model getModel() {
        return model;
    }

    public void setController(C controller) {
        this.controller = controller;
    }

    public Parent getParent() {
        return parent;
    }

    public abstract void initializeView();

    public abstract void initializeControls();
}
