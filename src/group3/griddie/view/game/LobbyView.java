package group3.griddie.view.game;

import group3.griddie.controller.game.LobbyController;
import group3.griddie.game.Lobby;
import group3.griddie.view.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LobbyView extends View<Lobby> {

    public LobbyView(Lobby lobby) {
        super(lobby, new AnchorPane());
    }

    @Override
    protected void initializeView() {
        AnchorPane root = (AnchorPane) getNode();

        Parent fxml = null;
        LobbyController controller = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            fxml = loader.load(getClass().getResource("LobbyView.fxml").openStream());
            controller = loader.getController();
            controller.setModel((Lobby) getModel());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(controller);

        root.getChildren().add(fxml);
    }

    @Override
    protected void initializeControls() {

    }
}
