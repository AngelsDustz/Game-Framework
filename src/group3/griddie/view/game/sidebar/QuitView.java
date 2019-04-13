package group3.griddie.view.game.sidebar;

import group3.griddie.controller.Controller;
import group3.griddie.controller.game.QuitController;
import group3.griddie.view.View;
import group3.griddie.view.game.GameButton;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class QuitView extends View {

    public QuitView(Parent root, Controller controller) {
        super(root, controller);
        Pane root_ = (Pane) getRoot();
        QuitController controller_ = (QuitController) controller;
        GameButton quitButton = new GameButton("Quit", GameButton.Size.SMALL);
        root_.getChildren().addAll(quitButton);

        quitButton.setOnMouseClicked(event -> {
            controller_.quitGame();
        });

    }



}
