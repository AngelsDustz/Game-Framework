package group3.griddie.view.game.sidebar;

import group3.griddie.game.player.HumanPlayer;
import group3.griddie.view.View;
import group3.griddie.view.game.GameView;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class OpponentSelectView extends View {

    public OpponentSelectView(GameView gameView) {
        super(new GridPane(), null);

        GridPane root = (GridPane) getRoot();

        Button button1 = new Button("Human vs Human");
        Button button2 = new Button("Human vs AI");
        Button button3 = new Button("Human vs Remote");

        root.add(button1, 0, 0);
        root.add(button2, 0, 1);
        root.add(button3, 0, 2);

        button1.setOnMouseClicked(event -> {
            gameView.getGame().getLobby().join(new HumanPlayer());
            gameView.getGame().getLobby().join(new HumanPlayer());
        });

        button2.setOnMouseClicked(event -> {
            gameView.setSideBar(new DifficultySelectView(gameView));
        });
    }

}
