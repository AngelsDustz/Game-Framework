package group3.griddie.controller.menu;

import group3.griddie.controller.Controller;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.HashMap;

public class ChallengePlayerController extends Controller {

    private StackPane rootpane;
    private GridPane removeAbleChallengerPane;

    public ChallengePlayerController(StackPane rootpane, GridPane removeAbleChallengerPane){
        this.rootpane = rootpane;
        this.removeAbleChallengerPane = removeAbleChallengerPane;
    }

    public void removePane(){
        rootpane.getChildren().remove(removeAbleChallengerPane);
    }

    public ArrayList<String> getDate(){
        return new ArrayList<>();
    }

    public HashMap<String, ArrayList<String>> getPlayerList(){
        return new HashMap<>();
    }

    public void sendText(String text){
        //sending text
    }
}
