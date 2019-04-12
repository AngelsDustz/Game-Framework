package group3.griddie;

import group3.griddie.game.Game;
import group3.griddie.viewOLD.menu.main.MainMenuViewOLD;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
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

        MainMenuViewOLD menu = new MainMenuViewOLD();
        menu.init();

        stage.setScene(new Scene(menu.getParent()));
    }

    public static void launchGame(Game game) {
        Griddie.game = game;
        game.init();
        stage.setScene(game);
    }
}
