package group3.griddie.viewOLD.menu.main;

import group3.griddie.viewOLD.RootViewOLD;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenuViewOLD extends RootViewOLD {
    public MainMenuViewOLD() {
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
