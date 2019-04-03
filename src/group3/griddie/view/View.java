package group3.griddie.view;

import group3.griddie.controller.Controller;
import group3.griddie.model.Model;
import javafx.scene.Node;

public abstract class View<C extends Controller> {

    private C controller;
    private Model model;
    private Node node;

    public View(Model model, Node node) {
        this.model = model;
        this.node = node;
    }

    public void init() {
        initializeView();
    }

    public C getController() {
        return controller;
    }

    public Model getModel() {
        return model;
    }

    public void setController(C controller) {
        this.controller = controller;

        initializeControls();
    }

    public Node getNode() {
        return node;
    }

    protected abstract void initializeView();
    protected abstract void initializeControls();
}
