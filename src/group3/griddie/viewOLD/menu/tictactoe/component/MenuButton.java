package group3.griddie.viewOLD.menu.tictactoe.component;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class MenuButton extends Button {

    private static final int PADDING = 20;

    public MenuButton(String text) {
        setText(text);
        setPadding(new Insets(PADDING));
    }

}
