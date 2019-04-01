package group3.griddie.view.menu.main;

import group3.griddie.controller.Controller;
import group3.griddie.controller.menu.MainMenuController;
import group3.griddie.view.View;
import group3.griddie.view.menu.main.component.GameButton;
import javafx.scene.layout.VBox;

public class MainMenuView extends View {

    private GameButton ortelloButton;
    private GameButton ticTacToeButton;

    public MainMenuView() {
        super(null, new VBox());
    }

    @Override
    protected Controller createController() {
        return new MainMenuController(this);
    }

    @Override
    protected void createView() {
        VBox root = (VBox) getParent();
        root.getChildren().add(ortelloButton = new GameButton("Ortello"));
        root.getChildren().add(ticTacToeButton = new GameButton("TicTacToe"));
    }

    @Override
    protected void createControls() {
        MainMenuController controller = (MainMenuController) getController();
        ortelloButton.setOnMouseClicked(event -> controller.startOrtello());
        ticTacToeButton.setOnMouseClicked(event -> controller.startTicTacToe());
    }
}
