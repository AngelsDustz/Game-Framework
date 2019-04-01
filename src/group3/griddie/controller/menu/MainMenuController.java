package group3.griddie.controller.menu;

import group3.griddie.controller.Controller;
import group3.griddie.view.menu.main.MainMenuView;

public class MainMenuController extends Controller {

    public MainMenuController(MainMenuView view) {
        super(view, null);
    }

    public void startOrtello() {
        System.out.println("Starting ortello");
    }

    public void startTicTacToe() {
        System.out.println("Starting tic tac toe");
    }

}
