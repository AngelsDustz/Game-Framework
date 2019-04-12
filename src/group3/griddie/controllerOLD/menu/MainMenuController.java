package group3.griddie.controllerOLD.menu;

import group3.griddie.Griddie;
import group3.griddie.controllerOLD.Controller;
import group3.griddie.game.othello.Othello;
import group3.griddie.game.tictactoe.TicTacToe;
import group3.griddie.network.NetworkHelperThread;
import group3.griddie.network.bufferThread;

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
        TicTacToe ticTacToe = new TicTacToe("Tic-tac-toe");
        Griddie.launchGame(ticTacToe);
    }
}
