package group3.griddie.controller.game;

import group3.griddie.Griddie;
import group3.griddie.controller.Controller;
import group3.griddie.game.Game;

public class QuitController extends Controller {
    private Game game;

    public QuitController(Game game){
        this.game = game;
    }
    public void quitGame(){
        Griddie.launchGame(game);
    }
}
