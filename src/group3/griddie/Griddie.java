package group3.griddie;

import group3.griddie.controller.menu.MainMenuController;
import group3.griddie.game.Game;
import group3.griddie.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Griddie extends Application {
    private static Stage stage;
    private static Game game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.show();

        newScene((new MainMenuController()).getView());
    }

    public static void launchGame(Game game) {
        Griddie.game = game;
        stage.setScene(new Scene(game.getPane()));
        game.init();
    }

    public static void newScene(View view) {
        stage.setScene(new Scene(view.getParent()));
    }

}
