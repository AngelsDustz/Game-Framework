package group3.griddie.view.menu.main.component;

import group3.griddie.game.Game;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class GameButton extends Button {

    private static final int PADDING = 20;

    public GameButton(String title) {
        setText(title);
        setPadding(new Insets(PADDING));
        setPrefWidth(200);
    }

}
