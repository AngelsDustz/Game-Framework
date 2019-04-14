package group3.griddie.controller.menu;

import group3.griddie.controller.Controller;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LobbyController extends Controller {

    private StackPane pane;
    private VBox box;

    public void setRoot(StackPane pane){
        this.pane = pane;
    }

    public void setMenu(VBox box){
        this.box = box;
    }

    public void removePane(){
        this.pane.getChildren().remove(box);
    }

    public void addPane(GridPane box){
        this.pane.getChildren().add(box);
    }
}
