package group3.griddie.view.game.menu;

import group3.griddie.controller.Controller;
import group3.griddie.controller.menu.MainMenuController;
import group3.griddie.view.View;
import group3.griddie.view.game.GameButton;
import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MainMenuButtonsView extends View {

    public MainMenuButtonsView(Parent root, Controller controller) {
        super(root, controller);

        GridPane root_ = (GridPane) getRoot();
        MainMenuController controller_ = (MainMenuController) getController();

        GameButton button1 = new GameButton("Orthello", GameButton.Size.MEDIUM, "text-button");
        GameButton button2 = new GameButton("Tic-tac-toe", GameButton.Size.MEDIUM, "text-button");
        GameButton button3 = new GameButton("Quit", GameButton.Size.SMALL, "text-button");

        root_.getColumnConstraints().add(new ColumnConstraints( 170));
        root_.getRowConstraints().add(new RowConstraints(50));

        root_.add(button1, 1, 1);
        root_.add(button2, 1, 2);
        root_.add(button3, 1, 3);

        button1.setOnMouseClicked(event -> {
            controller_.startOthello();
        });

        button2.setOnMouseClicked(event -> {
            controller_.startTicTacToe();
        });

        button3.setOnMouseClicked(event -> {
            controller_.closeGriddie();
        });
    }
}
