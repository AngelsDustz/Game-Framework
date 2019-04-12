package group3.griddie.controllerOLD;

import group3.griddie.model.Model;
import group3.griddie.viewOLD.ViewOLD;

public abstract class Controller<M extends Model> {
    private M model;
    private ViewOLD view;

    public Controller() {

    }

    public Controller(M model) {
        this.model = model;
    }

    public M getModel() {
        return model;
    }

    public ViewOLD getView() {
        return view;
    }

    public void setView(ViewOLD view) {
        this.view = view;
    }

    public void setModel(M model) {
        this.model = model;
    }
}
