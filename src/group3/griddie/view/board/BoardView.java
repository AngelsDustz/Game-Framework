package group3.griddie.view.board;

import group3.griddie.controller.Controller;
import group3.griddie.model.Model;
import group3.griddie.view.View;
import javafx.scene.layout.GridPane;

public class BoardView extends View {

    public BoardView(Model model) {
        super(model, new GridPane());
    }

    @Override
    protected Controller createController() {
        return null;
    }

    @Override
    protected void createView() {

    }

    @Override
    protected void createControls() {

    }
}
