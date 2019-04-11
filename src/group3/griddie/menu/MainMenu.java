package group3.griddie.menu;

import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MainMenu extends Menu {

    private class MenuButton extends Button {

        public MenuButton(String text) {
            super(text);

            setPrefWidth(200);
            setPrefHeight(100);
        }

    }

    public MainMenu() {
        super(new GridPane());

        GridPane root = (GridPane) getRoot();

        MenuButton button1 = new MenuButton("Othello");

        button1.setOnMouseClicked(event -> {

        });

        root.add(button1, 0 ,0);
    }

}
