package group3.griddie.controller;

import group3.griddie.model.Model;
import group3.griddie.view.View;

public abstract class Controller<M extends Model> {
    private M model;
    private View view;

    public Controller(M model) {
        this.model = model;
        view = createView();

        view.initializeView();
        view.initializeControls();
    }

    protected abstract View createView();

    public M getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}
