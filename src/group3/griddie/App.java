package group3.griddie;

import group3.griddie.view.RootView;
import group3.griddie.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.show();

        newScene(new RootView());
    }

    public static void newScene(View view) {
        stage.setScene(new Scene(view.getParent()));
    }

}
