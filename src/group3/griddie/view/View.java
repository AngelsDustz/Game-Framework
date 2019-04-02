package group3.griddie.view;

import group3.griddie.controller.Controller;

public abstract class View<C extends Controller> {
    private C controller;

    public View() {
    }

    public C getController() {
        return controller;
    }

    public void setController(C controller) {
        this.controller = controller;
    }
}
