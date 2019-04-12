package group3.griddie.controller;

import group3.griddie.model.Model;
import group3.griddie.view.View;

public class Controller<M extends Model> {
    private M model;
    private View view;

    public Controller() {

    }

    public Controller(M model) {
        this.model = model;
    }

    public M getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setModel(M model) {
        this.model = model;
    }
}
