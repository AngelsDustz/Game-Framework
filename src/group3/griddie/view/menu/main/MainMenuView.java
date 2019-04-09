package group3.griddie.view.menu.main;

import group3.griddie.controller.menu.MainMenuController;
import group3.griddie.view.RootView;
import group3.griddie.view.menu.main.component.GameButton;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenuView extends RootView {
    public MainMenuView() {
        super(null, new VBox());
    }

    @Override
    public void initializeView() {
        VBox root = (VBox) getParent();
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        root.getChildren().add(fxml);
    }

    @Override
    protected void initializeControls() {
    }
}
