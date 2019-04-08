package group3.griddie.controller.menu;

import group3.griddie.Griddie;
import group3.griddie.controller.Controller;
import group3.griddie.game.ortello.Ortello;
import group3.griddie.game.tictactoe.TicTacToe;
import group3.griddie.view.View;
import group3.griddie.view.menu.main.MainMenuView;

public class MainMenuController extends Controller {

    public MainMenuController() {
        super(null);
    }

    public void startOrtello() {
        System.out.println("Starting ortello");

        Griddie.launchGame(new Ortello("Reversie"));
    }

    public void startTicTacToe() {
        System.out.println("Starting tic tac toe");

        Griddie.launchGame(new TicTacToe("Tic-tac-toe"));
    }
}
