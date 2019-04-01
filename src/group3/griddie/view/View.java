package group3.griddie.view;

import group3.griddie.controller.Controller;
import group3.griddie.model.Model;
import javafx.scene.Parent;

public abstract class View {

    private Model model;
    private Parent parent;
    private Controller controller;

    public View(Model model, Parent parent) {
        this.model = model;
        this.parent = parent;

        controller = createController();

        createView();
        createControls();
    }

    protected void setController(Controller controller) {
        this.controller = controller;
    }

    protected Controller getController() {
        return controller;
    }

    protected abstract Controller createController();

    public Parent getParent() {
        return parent;
    }

    protected abstract void createView();

    protected abstract void createControls();
}
