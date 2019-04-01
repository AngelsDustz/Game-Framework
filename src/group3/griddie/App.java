package group3.griddie;

import group3.griddie.controller.menu.MainMenuController;
import group3.griddie.game.Game;
import group3.griddie.view.View;
import group3.griddie.view.menu.main.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.show();

        newScene(new MainMenuView());
    }

    public void launchGame(Game game) {
        game.init();
    }

    public static void newScene(View view) {
        stage.setScene(new Scene(view.getParent()));
    }

}
