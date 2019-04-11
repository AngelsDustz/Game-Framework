package group3.griddie.view;

import group3.griddie.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class View extends AnchorPane {

    private Controller controller;
    private Parent root;

    public View(Parent root, Controller controller) {
        this.root = root;
        this.controller = controller;

        getChildren().add(root);
    }

    public Controller getController() {
        return controller;
    }

    public Parent getRoot() {
        return root;
    }
}
