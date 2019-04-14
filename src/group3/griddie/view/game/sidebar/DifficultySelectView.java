package group3.griddie.view.game.sidebar;

import group3.griddie.game.ai.TicTacToeAI;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.view.View;
import group3.griddie.view.game.GameView;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class DifficultySelectView extends View {

    public DifficultySelectView(GameView gameView) {
        super(new GridPane(), null);

        GridPane root = (GridPane) getRoot();

        Button button1 = new Button("Easy");
        Button button2 = new Button("Medium");
        Button button3 = new Button("Hard");
        Button button4 = new Button("Back");

        root.add(button1, 0, 0);
        root.add(button2, 0, 1);
        root.add(button3, 0, 2);
        root.add(button4, 0, 3);


        button3.setOnMouseClicked(event -> {
            gameView.getGame().getLobby().join(new HumanPlayer("Human"));

            AIPlayer aiPlayer = new AIPlayer(AIPlayer.Difficulty.DIFFICULTY_HARD);
            aiPlayer.setGameAI(new TicTacToeAI(gameView.getGame(), aiPlayer));

            gameView.getGame().getLobby().join(aiPlayer);
        });
    }

}
