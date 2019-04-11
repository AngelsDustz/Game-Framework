package group3.griddie.viewOLD.menu.tictactoe;

import group3.griddie.controllerOLD.menu.TicTacToeMenuController;
import group3.griddie.viewOLD.RootViewOLD;
import group3.griddie.viewOLD.menu.tictactoe.component.MenuButton;
import javafx.scene.layout.VBox;

public class TicTacToeMenuViewOLD extends RootViewOLD {

    private MenuButton startButton;
    private MenuButton quitButton;

    public TicTacToeMenuViewOLD() {
        super(null, new VBox());
    }

    @Override
    public void initializeView() {
        VBox root = (VBox) getParent();
        root.getChildren().add(startButton = new MenuButton("Start"));
        root.getChildren().add(quitButton = new MenuButton("Quit"));
    }

    @Override
    public void initializeControls() {
        TicTacToeMenuController controller = (TicTacToeMenuController) getController();
    }
}
