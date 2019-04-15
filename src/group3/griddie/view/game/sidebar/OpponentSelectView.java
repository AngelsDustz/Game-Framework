package group3.griddie.view.game.sidebar;

import group3.griddie.controller.Controller;
import group3.griddie.controller.menu.LobbyController;
import group3.griddie.view.View;
import group3.griddie.view.game.GameButton;
import group3.griddie.view.game.GameView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class OpponentSelectView extends View {


    public OpponentSelectView(GameView gameView, StackPane pane, VBox box, GridPane challengeMenu) {
        super(new GridPane(), new LobbyController());

        GridPane root = (GridPane) getRoot();
        Controller controller = (LobbyController) getController();

        GameButton button1 = new GameButton("Human vs Human", GameButton.Size.MEDIUM, "text-button-game");
        GameButton button2 = new GameButton("Human vs AI", GameButton.Size.MEDIUM, "text-button-game");
        GameButton button3 = new GameButton("Human vs Remote", GameButton.Size.MEDIUM, "text-button-game");
        GameButton button4 = new GameButton("AI vs Remote", GameButton.Size.MEDIUM, "text-button-game");

        root.getColumnConstraints().add(new ColumnConstraints(1000));
        root.add(button1, 0, 0);
        root.add(button2, 0, 1);
        root.add(button3, 0, 2);
        root.add(button4, 0,3);

        button1.setOnMouseClicked(event -> {
            ((LobbyController) controller).setRoot(pane);
            ((LobbyController) controller).setMenu(box);
            ((LobbyController) controller).removePane();
        });

        button2.setOnMouseClicked(event -> {
            ((LobbyController) controller).setRoot(pane);
            ((LobbyController) controller).setMenu(box);
            ((LobbyController) controller).removePane();
            gameView.getGame().startAiGame();
        });

        button3.setOnMouseClicked(event -> {
            ((LobbyController) controller).setRoot(pane);
            ((LobbyController) controller).setMenu(box);
            ((LobbyController) controller).removePane();
            ((LobbyController) controller).addPane(challengeMenu);
            gameView.getGame().startHumanVsRemote();
        });

        button4.setOnMouseClicked(event -> {
            ((LobbyController) controller).setRoot(pane);
            ((LobbyController) controller).setMenu(box);
            ((LobbyController) controller).removePane();
            ((LobbyController) controller).addPane(challengeMenu);
             gameView.getGame().startAiVsRemote();
        });

    }

    public void addChallengeMenu(){

    }

}
