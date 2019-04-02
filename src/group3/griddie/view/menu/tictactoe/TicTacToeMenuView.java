package group3.griddie.view.menu.tictactoe;

import group3.griddie.controller.menu.TicTacToeMenuController;
import group3.griddie.view.RootView;
import group3.griddie.view.menu.tictactoe.component.MenuButton;
import javafx.scene.layout.VBox;

public class TicTacToeMenuView extends RootView<TicTacToeMenuController> {
    private MenuButton startButton;
    private MenuButton quitButton;

    public TicTacToeMenuView(TicTacToeMenuController controller) {
        super(null, new VBox());

        this.setController(controller);
    }

    @Override
    public void initializeView() {
        VBox root = (VBox) getParent();
        root.getChildren().add(startButton = new MenuButton("Start"));
        root.getChildren().add(quitButton = new MenuButton("Quit"));
    }

    @Override
    public void initializeControls() {
        TicTacToeMenuController controller = getController();
    }
}
