package group3.griddie;

import group3.griddie.controller.menu.MainMenuController;
import group3.griddie.game.Game;
import group3.griddie.view.RootView;
import group3.griddie.view.View;
import group3.griddie.view.menu.main.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Griddie extends Application {
    private static Stage stage;
    private static Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.show();

        MainMenuView menu = new MainMenuView();
        menu.init();

        menu.setController(new MainMenuController());

        stage.setScene(new Scene(menu.getParent()));
    }

    public static void launchGame(Game game) {
        Griddie.game = game;
        game.init();
        stage.setScene(game);

        try {
            game.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
