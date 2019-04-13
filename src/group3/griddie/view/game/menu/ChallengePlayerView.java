package group3.griddie.view.game.menu;

import group3.griddie.Griddie;
import group3.griddie.controller.Controller;
import group3.griddie.controller.menu.ChallengePlayerController;
import group3.griddie.view.View;
import group3.griddie.view.game.GameButton;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ChallengePlayerView extends View {

    public ChallengePlayerView(Parent root, Controller controller) {
        super(root, controller);

        GridPane root_ = (GridPane) getRoot();
        ChallengePlayerController controller_ = (ChallengePlayerController) getController();

        Label tablePlayerLabel = new Label("PlayerList");
        Label tableChallengerLabel = new Label("Players Challenging You");
        TableView tablePlayer = new TableView();
        TableView tableChallengers = new TableView();

        tablePlayer.setEditable(true);
        tableChallengers.setEditable(true);

        TableColumn Player = new TableColumn("Player name");
        TableColumn Game = new TableColumn("Game");
        GameButton button = new GameButton("Get Player List", GameButton.Size.SMALL, "text-button-game-right");
        GameButton button1 = new GameButton("Send command", GameButton.Size.SMALL, "text-button-game-right");
        TextField inputField = new TextField();

        button.setOnMouseClicked(event -> {

        });

        button1.setOnMouseClicked(event -> {
            controller_.removePane();
        });

        tablePlayer.getColumns().add(Player);
        tableChallengers.getColumns().addAll(Player, Game);

        root_.add(tablePlayerLabel, 0,0);
        root_.add(tableChallengerLabel, 1,0);
        root_.add(tablePlayer, 0, 1);
        root_.add(tableChallengers, 1, 1);
        root_.add(inputField, 0, 2);
        root_.add(button1, 1,2);
        root_.add(button, 1, 3);

        BackgroundImage image = new BackgroundImage(new Image("/assets/images/middle.png"), null, null,null, null);
        root_.setBackground(new Background(image));
    }

}
