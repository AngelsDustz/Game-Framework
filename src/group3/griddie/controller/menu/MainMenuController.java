package group3.griddie.controller.menu;

import group3.griddie.App;
import group3.griddie.controller.Controller;
import group3.griddie.game.ortello.Ortello;
import group3.griddie.game.tictactoe.TicTacToe;
import group3.griddie.view.View;
import group3.griddie.view.menu.main.MainMenuView;

public class MainMenuController extends Controller {

    public MainMenuController() {
        super(null);
    }

    @Override
    protected View createView() {
        return new MainMenuView(this);
    }

    public void startOrtello() {
        System.out.println("Starting ortello");

        App.launchGame(new Ortello());
    }

    public void startTicTacToe() {
        System.out.println("Starting tic tac toe");

        App.launchGame(new TicTacToe());
    }
}
