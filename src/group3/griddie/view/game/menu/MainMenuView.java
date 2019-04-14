package group3.griddie.view.game.menu;

import group3.griddie.controller.Controller;
import group3.griddie.controller.menu.MainMenuController;
import group3.griddie.view.View;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class MainMenuView extends View {

    public MainMenuView(Parent root, Controller controller) {
        super(root, controller);
        BorderPane mainMenuRoot = (BorderPane) getRoot();
        mainMenuRoot.setStyle("-fx-border-color: black;");

        MainMenuButtonsView buttonsView = new MainMenuButtonsView(new GridPane(), new MainMenuController());

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();

        Text topText = new Text(150, 140, "Griddie");
        Pane topPane = new Pane();
        topPane.getStyleClass().add("title");
        Pane bottomPane = new Pane();

        BackgroundSize backgroundSize = new BackgroundSize(300, 300, true, true, true, false);
        BackgroundImage image = new BackgroundImage(new Image("/assets/images/top.png"), null, null,null, backgroundSize);
        topPane.setBackground(new Background(image));
        image = new BackgroundImage(new Image("/assets/images/bottom.png"), null, null,null, backgroundSize);
        bottomPane.setBackground(new Background(image));
        image = new BackgroundImage(new Image("/assets/images/middle.png"), null, null,null, null);
        mainMenuRoot.setBackground(new Background(image));

        topPane.getChildren().add(topText);
        bottomPane.setPrefSize(675, 127);
        mainMenuRoot.setTop(topPane);
        mainMenuRoot.setCenter(buttonsView);
        mainMenuRoot.setBottom(bottomPane);

    }


}
