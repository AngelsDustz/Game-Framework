package group3.griddie;

import group3.griddie.controller.menu.MainMenuController;
import group3.griddie.game.Game;
import group3.griddie.view.game.menu.MainMenuView;
import group3.griddie.viewOLD.menu.main.MainMenuViewOLD;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Griddie extends Application {
    private static Stage stage;
    private static Game game;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Font.loadFont("assets/fonts/Fipps-Regular.otf", 12);

        stage = primaryStage;
        stage.setTitle("Griddie Game Framework");
        stage.show();
        launchMenu();
    }

    public static Stage getStage(){
        return stage;
    }

    public static void launchMenu(){
        BorderPane mainMenu = new BorderPane();
        MainMenuView menu = new MainMenuView(mainMenu, null);
        Scene scene = new Scene(menu);
        scene.getStylesheets().add(Griddie.class.getResource("/assets/css/custom-font-style.css").toExternalForm());
        stage.setScene(scene);
    }

    public static void launchGame(Game game) {
        Griddie.game = game;
        game.init();
        game.getStylesheets().add(Griddie.class.getResource("/assets/css/custom-font-style.css").toExternalForm());
        stage.setScene(game);
    }
}
