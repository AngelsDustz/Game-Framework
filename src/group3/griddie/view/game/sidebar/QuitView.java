package group3.griddie.view.game.sidebar;

import group3.griddie.controller.Controller;
import group3.griddie.controller.game.QuitController;
import group3.griddie.game.Game;
import group3.griddie.view.View;
import group3.griddie.view.game.GameButton;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class QuitView extends View {

    public QuitView(Parent root, Controller controller, Game game) {
        super(root, controller);
        Pane root_ = (Pane) getRoot();
        QuitController controller_ = (QuitController) controller;
        GameButton quitButton = new GameButton("Quit", GameButton.Size.SMALL, "text-button-game");
        root_.getChildren().addAll(quitButton);

        quitButton.setOnMouseClicked(event -> {
            if (game.isRunning()) {
                game.stop();
            }
            controller_.quitGame();
        });

    }



}
