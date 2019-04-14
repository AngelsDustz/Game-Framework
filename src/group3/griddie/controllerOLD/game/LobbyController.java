package group3.griddie.controllerOLD.game;

import group3.griddie.controllerOLD.Controller;
import group3.griddie.game.Lobby;
import group3.griddie.game.ai.TicTacToeAI;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.model.board.actor.Actor;

public class LobbyController extends Controller<Lobby> {

    public void humanVersusHuman() {
        Lobby lobby = getModel();
    }

    public void humanVersusAI() {
        Lobby lobby = getModel();
    }

    public void humanVersusRemote() {
        Lobby lobby = getModel();
    }


}
