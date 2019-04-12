package group3.griddie;

import group3.griddie.game.Game;
import group3.griddie.menu.MainMenu;
import group3.griddie.network.NetworkHelperThread;
import group3.griddie.viewOLD.menu.main.MainMenuViewOLD;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Griddie extends Application {
    private static Stage stage;
    private static Game game;
    public static NetworkHelperThread networkHelperThread = new NetworkHelperThread("127.0.0.1", 7789);
    public Thread networkThread = new Thread(networkHelperThread);

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Griddie Game Framework");
        stage.show();
        networkThread.start();

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
