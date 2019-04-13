package group3.griddie.view.game.sidebar;

import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.player.RemotePlayer;
import group3.griddie.view.View;
import group3.griddie.view.game.GameButton;
import group3.griddie.view.game.GameView;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class OpponentSelectView extends View {

    public OpponentSelectView(GameView gameView) {
        super(new GridPane(), null);

        GridPane root = (GridPane) getRoot();

        GameButton button1 = new GameButton("Human vs Human", GameButton.Size.SMALL, "text-button-game");
        GameButton button2 = new GameButton("Human vs AI", GameButton.Size.SMALL, "text-button-game");
        GameButton button3 = new GameButton("Human vs Remote", GameButton.Size.SMALL, "text-button-game");
        GameButton button4 = new GameButton("AI vs Remote", GameButton.Size.SMALL, "text-button-game");

        root.add(button1, 0, 0);
        root.add(button2, 0, 1);
        root.add(button3, 0, 2);
        root.add(button4, 0,3);

        button1.setOnMouseClicked(event -> {
            gameView.getGame().getLobby().join(new HumanPlayer());
            gameView.getGame().getLobby().join(new HumanPlayer());
        });

        button2.setOnMouseClicked(event -> {
            gameView.setSideBar(new DifficultySelectView(gameView));
        });

        button3.setOnMouseClicked(event -> {
            gameView.getGame().getLobby().join(new HumanPlayer());
            gameView.getGame().getLobby().join(new RemotePlayer());
        });

    }

}
