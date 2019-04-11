package group3.griddie.controllerOLD.game;

import group3.griddie.controllerOLD.Controller;
import group3.griddie.game.Lobby;
import group3.griddie.game.ai.TicTacToeAI;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.player.RemotePlayer;
import group3.griddie.model.board.actor.Actor;

public class LobbyController extends Controller<Lobby> {

    public void humanVersusHuman() {
        Lobby lobby = getModel();

        lobby.join(new HumanPlayer(lobby.getGame(), Actor.Type.TYPE_1, "Player 1"));
        lobby.join(new HumanPlayer(lobby.getGame(), Actor.Type.TYPE_2, "Player 2"));
    }

    public void humanVersusAI() {
        Lobby lobby = getModel();

        AIPlayer aiPlayer = new AIPlayer(lobby.getGame(), Actor.Type.TYPE_2, "AI Player");
        aiPlayer.setDifficulty(AIPlayer.Difficulty.DIFFICULTY_HARD);
        aiPlayer.setGameAI(new TicTacToeAI(lobby.getGame(), aiPlayer));

        lobby.join(new HumanPlayer(lobby.getGame(), Actor.Type.TYPE_1, "Player 1"));
        lobby.join(aiPlayer);
    }

    public void humanVersusRemote() {
        Lobby lobby = getModel();

        lobby.join(new HumanPlayer(lobby.getGame(), Actor.Type.TYPE_1, "Player 1"));
        //lobby.join(new RemotePlayer(lobby.getGame(), Actor.Type.TYPE_2, "Player 2"));
    }

}
