package group3.griddie.view.menu.main;

import group3.griddie.controller.menu.MainMenuController;
import group3.griddie.view.RootView;
import group3.griddie.view.menu.main.component.GameButton;
import javafx.scene.layout.VBox;

public class MainMenuView extends RootView<MainMenuController> {
    private GameButton ortelloButton;
    private GameButton ticTacToeButton;

    public MainMenuView() {
        super(null, new VBox());
    }

    @Override
    public void initializeView() {
        VBox root = (VBox) getParent();
        root.getChildren().add(ortelloButton = new GameButton("Ortello"));
        root.getChildren().add(ticTacToeButton = new GameButton("TicTacToe"));
    }

    @Override
    public void initializeControls() {
        ortelloButton.setOnMouseClicked(event -> getController().startOrtello());
        ticTacToeButton.setOnMouseClicked(event -> getController().startTicTacToe());
    }
}
