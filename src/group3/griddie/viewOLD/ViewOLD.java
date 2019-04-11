package group3.griddie.viewOLD;

import group3.griddie.controllerOLD.Controller;
import group3.griddie.model.Model;
import javafx.scene.Node;

public abstract class ViewOLD<M extends Model> {

    private Controller<M> controller;
    private Model model;
    private Node node;

    public ViewOLD(Model model, Node node) {
        this.model = model;
        this.node = node;
    }

    public void init() {
        initializeView();
    }

    public Controller<M> getController() {
        return controller;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;

        initializeView();
    }

    public void setController(Controller<M> controller) {
        this.controller = controller;

        initializeControls();
    }

    public Node getNode() {
        return node;
    }

    protected abstract void initializeView();
    protected abstract void initializeControls();
}
