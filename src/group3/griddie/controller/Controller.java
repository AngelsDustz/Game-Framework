package group3.griddie.controller;

import group3.griddie.model.Model;
import group3.griddie.view.View;

public abstract class Controller {

    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        view = createView();

        view.initializeView();
        view.initializeControls();
    }

    protected abstract View createView();

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}
