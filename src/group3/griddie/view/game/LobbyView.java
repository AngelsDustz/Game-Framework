package group3.griddie.view.game;

import group3.griddie.game.Lobby;
import group3.griddie.view.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LobbyView extends View<Lobby> {

    public LobbyView(Lobby lobby) {
        super(lobby, new AnchorPane());
    }

    @Override
    protected void initializeView() {
        AnchorPane root = (AnchorPane) getNode();

        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource("LobbyView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        root.getChildren().add(fxml);
    }

    @Override
    protected void initializeControls() {

    }
}
