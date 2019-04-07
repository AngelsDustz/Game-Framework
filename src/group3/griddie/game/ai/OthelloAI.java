package group3.griddie.game.ai;

import group3.griddie.game.Game;
import group3.griddie.game.othello.Othello;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.model.board.Cell;

import java.util.ArrayList;
import java.util.Random;

public class OthelloAI implements AI {
    private Othello game;
    private AIPlayer    player;

    public OthelloAI(Game game, AIPlayer player) {
        if (!(game instanceof Othello)) {
            throw new IllegalArgumentException();
        }

        this.game   = (Othello) game;
        this.player = player;
    }

    @Override
    public Cell predictMove() {
        ArrayList<Cell> legitMoves = game.getLegalMoves(game.getBoard(), player.getActorType());

        return legitMoves.get(new Random().nextInt(legitMoves.size()));
    }
}
