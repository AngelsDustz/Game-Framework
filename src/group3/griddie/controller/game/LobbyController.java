package group3.griddie.controller.game;

import group3.griddie.controller.Controller;
import group3.griddie.game.Lobby;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.player.RemotePlayer;
import group3.griddie.model.board.actor.Actor;

public class LobbyController extends Controller<Lobby> {

    public LobbyController(Lobby model) {
        super(model);
    }

    public void humanVersusHuman() {
        Lobby lobby = (Lobby) getModel();

        lobby.join(new HumanPlayer(lobby.getGame(), Actor.Type.TYPE_1, "Player 1"));
        lobby.join(new HumanPlayer(lobby.getGame(), Actor.Type.TYPE_2, "Player 2"));
    }

    public void humanVersusAI() {
        Lobby lobby = (Lobby) getModel();

        lobby.join(new HumanPlayer(lobby.getGame(), Actor.Type.TYPE_1, "Player 1"));
        lobby.join(new AIPlayer(lobby.getGame(), Actor.Type.TYPE_1, "Player 2"));
    }

    public void humanVersusRemote() {
        Lobby lobby = (Lobby) getModel();

        lobby.join(new HumanPlayer(lobby.getGame(), Actor.Type.TYPE_1, "Player 1"));
        lobby.join(new RemotePlayer(lobby.getGame(), Actor.Type.TYPE_1, "Player 2"));
    }

}
