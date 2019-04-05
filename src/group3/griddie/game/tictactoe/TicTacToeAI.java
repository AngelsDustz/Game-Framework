package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.model.board.Board;
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
        switch(player.getDifficulty()) {
            case DIFFICULTY_EASY:
                return this.pickRandomCell();

            case DIFFICULTY_MEDIUM:
                return this.pickSemiRandomCell();

            case DIFFICULTY_HARD:
                return this.pickSmartCell();
        }
    }

    private Cell pickRandomCell() {
        ArrayList<Cell> freeCells   = this.game.getBoard().getFreeSpots();
        Random random               = new Random();

        return freeCells.get(random.nextInt(freeCells.size()));
    }

    /**
     * 1. Find first cell that is not ours.
     *
     */
    private Cell pickSemiRandomCell() {
        Board board = this.game.getBoard();
    }

    private Cell pickSmartCell() {
        //
    }
}
