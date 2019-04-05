package group3.griddie.view.game;

import group3.griddie.game.player.Player;
import group3.griddie.view.View;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class PlayerView extends View<Player> {

    public PlayerView(Player player) {
        super(player, new StackPane());
    }

    @Override
    protected void initializeView() {
        StackPane root = (StackPane) getNode();
        Player player = (Player) getModel();

        root.getChildren().add(new Text(player.getName()));
    }

    @Override
    protected void initializeControls() {

    }
}
