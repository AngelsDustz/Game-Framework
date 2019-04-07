package group3.griddie.game.ai;

import group3.griddie.game.Game;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.tictactoe.TicTacToe;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.TicTacToeActor;

import java.util.ArrayList;
import java.util.Random;

public class TicTacToeAI implements AI {
    private TicTacToe game;
    private AIPlayer    player;

    public TicTacToeAI(Game game, AIPlayer player) {
        if (!(game instanceof TicTacToe)) {
            throw new IllegalArgumentException();
        }

        this.game   = (TicTacToe) game;
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

            default:
                return null;
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
        Board board             = this.game.getBoard();
        Actor.Type actorType    = this.player.getActorType();

        Cell firstFree = board.findFirstActorTypeNotEqual(actorType);
        System.out.println(firstFree);

        return pickRandomCell();
    }

    private Cell pickSmartCell() {
        int bestVal = Integer.MIN_VALUE;
        Cell move   = null;

        Board testBoard = this.game.getBoard();
        System.out.println("Board:");
        System.out.println(testBoard);
        long start = System.currentTimeMillis();
        for (int i=0;i<testBoard.getWidth();i++) {
            for (int c=0;c<testBoard.getHeight();c++) {
                Cell cell = testBoard.getCell(i, c);

                if (cell.getOccupant() == null) {

                    cell.testOccupant(new TicTacToeActor(this.player.getActorType()));

                    int val = minimax(testBoard, 0, false);

                    cell.testOccupant(null);

                    if (val > bestVal) {
                        System.out.println(String.format("Found good move at col: %d row: %d with score %d", i, c, val));
                        bestVal = val;
                        move = cell;
                    }
                }
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("Minimax took: "+(end-start)+" ms.");

        return move;
    }

    // https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/
    private int minimax(Board board, int depth, boolean isMax) {
        Board newBoard  = new Board(board); // Force hard copy.
        Actor.Type type = game.checkIfWon(board);

        if (type != null) {
            if (type == this.player.getActorType()) {
                return 10 - depth;
            } else {
                return -10 + depth;
            }
        }

        if (newBoard.getFreeSpots().size() == 0) {
            return 0;
        }

        if (isMax) {
            int best = Integer.MIN_VALUE; //Set a base.

            for (Cell cell : newBoard.getFreeSpots()) {
                // Make move at cell.
                cell.testOccupant(new TicTacToeActor(Actor.Type.TYPE_2));

                best = Integer.max(best, minimax(newBoard, depth+1, false));

                // Undo move.
                cell.testOccupant(null);
            }

            return best;
        } else {
            int best = Integer.MAX_VALUE; //Set a base.

            for (Cell cell : newBoard.getFreeSpots()) {
                // Make move at cell.
                cell.testOccupant(new TicTacToeActor(Actor.Type.TYPE_1));

                best = Integer.min(best, minimax(newBoard, depth+1, true));

                // Undo move.
                cell.testOccupant(null);
            }

            return best;
        }
    }
}
