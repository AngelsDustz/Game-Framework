package group3.griddie.controllerOLD.menu;

import group3.griddie.Griddie;
import group3.griddie.controllerOLD.Controller;
import group3.griddie.game.othello.Othello;
import group3.griddie.game.tictactoe.TicTacToe;

public class MainMenuController extends Controller {

    public MainMenuController() {
        super(null);
    }

    public void startOthello() {
        System.out.println("Starting othello");

        Griddie.launchGame(new Othello());
    }

    public void startTicTacToe() {
        System.out.println("Starting tic tac toe");

        Griddie.launchGame(new TicTacToe("Tic-tac-toe"));
    }
}
