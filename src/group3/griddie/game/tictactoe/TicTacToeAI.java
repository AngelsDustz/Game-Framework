package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.model.board.Cell;

import java.util.ArrayList;
import java.util.Random;

public class TicTacToeAI {
    private Game        game;
    private AIPlayer    player;

    public TicTacToeAI(Game game, AIPlayer player) {
        this.game   = game;
        this.player = player;
    }

    public Cell predictMove() {
        return this.pickRandomCell();
    }

    private Cell pickRandomCell() {
        ArrayList<Cell> freeCells   = this.game.getBoard().getFreeSpots();
        Random random               = new Random();

        return freeCells.get(random.nextInt(freeCells.size()));
    }
}
