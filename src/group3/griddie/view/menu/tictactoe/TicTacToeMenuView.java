package group3.griddie.view.menu.tictactoe;

import group3.griddie.controller.Controller;
import group3.griddie.controller.menu.TicTacToeMenuController;
import group3.griddie.view.View;
import group3.griddie.view.menu.tictactoe.component.MenuButton;
import javafx.scene.layout.VBox;

public class TicTacToeMenuView extends View {

    private MenuButton startButton;
    private MenuButton quitButton;

    public TicTacToeMenuView() {
        super(null, new VBox());
    }

    @Override
    protected Controller createController() {
        return new TicTacToeMenuController(this);
    }

    @Override
    protected void createView() {
        VBox root = (VBox) getParent();
        root.getChildren().add(startButton = new MenuButton("Start"));
        root.getChildren().add(quitButton = new MenuButton("Quit"));
    }

    @Override
    protected void createControls() {
        TicTacToeMenuController controller = (TicTacToeMenuController) getController();
    }
}
