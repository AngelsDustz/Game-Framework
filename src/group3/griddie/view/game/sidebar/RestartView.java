package group3.griddie.view.game.sidebar;

import group3.griddie.Griddie;
import group3.griddie.controller.Controller;
import group3.griddie.game.Game;
import group3.griddie.view.View;
import group3.griddie.view.game.GameButton;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class RestartView extends View {

    public RestartView(Parent root, Controller controller, Game game) {
        super(root, controller);
        Pane root_ = (Pane) getRoot();
        GameButton button = new GameButton("Restart", GameButton.Size.SMALL, "text-button-game");
        root_.getChildren().add(button);

        button.setOnMouseClicked(event -> {
            Griddie.launchGame(game);
    });
    }
}
