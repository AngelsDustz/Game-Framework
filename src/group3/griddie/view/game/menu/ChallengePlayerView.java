package group3.griddie.view.game.menu;

import group3.griddie.Griddie;
import group3.griddie.controller.Controller;
import group3.griddie.controller.menu.ChallengePlayerController;
import group3.griddie.game.Game;
import group3.griddie.view.View;
import group3.griddie.view.game.GameButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChallengePlayerView extends View {
    private String[] playerlist;
    private int challengeNumber;

    public ChallengePlayerView(Parent root, Controller controller, Game game) {
        super(root, controller);

        GridPane root_ = (GridPane) getRoot();
        ChallengePlayerController controller_ = (ChallengePlayerController) getController();

        Label tablePlayerLabel = new Label("PlayerList");
        Label tableChallengerLabel = new Label("Players Challenging You");
        Label playerName = new Label("Player Name");
        Label challenger = new Label("Challenge Number");
        Label playerName1 = new Label("Player Name");
        tableChallengerLabel.getStyleClass().add("text-button");
        tablePlayerLabel.getStyleClass().add("text-button");
        playerName.getStyleClass().add("text-button-game-right");
        challenger.getStyleClass().add("text-button-game-right");
        playerName1.getStyleClass().add("text-button-game-right");

        ListView listView = new ListView();
        ListView listView1 = new ListView();
        ListView listView2 = new ListView();
        listView.setPrefWidth(300);
        listView1.setPrefWidth(300);
        listView2.setPrefWidth(300);
        listView.setEditable(true);
        listView1.setEditable(true);
        listView2.setEditable(true);

        GameButton button = new GameButton("Get Player List", GameButton.Size.SMALL, "text-button-game-right");
        GameButton button1 = new GameButton("Send Challenge", GameButton.Size.SMALL, "text-button-game-right");
        GameButton button2 = new GameButton("Subscribe", GameButton.Size.SMALL, "text-button-game-right");
        GameButton button3 = new GameButton("Accept Challenge", GameButton.Size.SMALL, "text-button-game-right");
        GameButton button4 = new GameButton("Close", GameButton.Size.SMALL, "text-button-game-right");

        button.setOnMouseClicked(event -> {
            game.getConnection().fetchPlayerList();
            String[] buttonPlayerList = playerlist;
            if (buttonPlayerList != null) {
                ObservableList<String> data = FXCollections.observableArrayList(playerlist);
                listView.setCellFactory(ComboBoxListCell.forListView(data));
                listView.getItems().clear();
                listView.setItems(data);
            }

            int buttonChallengeNumber = challengeNumber;
            listView2.getItems().add(buttonChallengeNumber);
        });

        button1.setOnMouseClicked(event -> {
            if(listView.getSelectionModel().getSelectedItem().toString() != null) {
                String name = listView.getSelectionModel().getSelectedItem().toString();
                game.getConnection().challenge(name, game.getName());
                controller_.removePane();
            }
        });

        button2.setOnMouseClicked(event -> {
            game.getConnection().subscribe(game.getName());
            controller_.removePane();
        });

        button3.setOnMouseClicked(event -> {
            int challengenumber = Integer.valueOf(listView2.getSelectionModel().getSelectedItem().toString());
            game.getConnection().challengeAccept(challengenumber);
            controller_.removePane();
        });

        button4.setOnMouseClicked(event -> {
            controller_.removePane();
        });


        root_.add(tablePlayerLabel, 0,0);
        root_.add(tableChallengerLabel, 1,0);
        //root_.add(tablePlayer, 0, 1);
        //root_.add(tableChallengers, 1, 1);
        root_.add(playerName,1,1);
        root_.add(challenger, 2, 1);
        root_.add(playerName1,0,1);
        root_.add(listView, 0,2);
        root_.add(listView1, 1,2);
        root_.add(listView2, 2,2);
        root_.add(button1, 0,3);
        root_.add(button2, 1,3);
        root_.add(button, 2, 3);
        root_.add(button3,0,4);
        root_.add(button4, 0, 5);

        BackgroundImage image = new BackgroundImage(new Image("/assets/images/middle.png"), null, null,null, null);
        root_.setBackground(new Background(image));

        game.getCommunication().playerListReceivedEvent.addListener(this::updatePlayerList);
        game.getCommunication().challengeReceivedEvent.addListener(this::updateChallengeNumberList);
    }

    private void updatePlayerList(String[] names){
        this.playerlist = names;
    }

    private void updateChallengeNumberList(int challengenumber){
        this.challengeNumber = challengenumber;
        System.out.println("recieved challenge number " + challengenumber);
    }
}
