package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.util.MiniMaxNode;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Random;

public class TicTacToeAI {
    private TicTacToe   game;
    private AIPlayer    player;
    private int counter =0;

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
        int mini = minimax(this.game.getBoard(), 10, false);
        System.out.println(String.format("Minimax returned %d after %d iterations", mini, this.counter));
//        return pickRandomCell();
        return null;
    }

    // https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/
    private int minimax(Board board, int depth, boolean isMaximizing) {
        board = new Board(board); // Force deep copy.
        this.counter++;
        System.out.println("Slots free: "+board.getFreeSpots().size());
        Actor.Type winner = game.checkIfWon(board);

        if (winner != null) {
            if (this.player.getActorType() == winner) {
                return 10;
            } else {
                return -10;
            }
        }

        // If we tried to do more calculations than our limit return 0.
        if (depth == 0) {
            return 0;
        }

        // Make sure you can flip between min and max.
        if (!isMaximizing) {
            int bestVal = Integer.MAX_VALUE;
            for (Cell cell : board.getFreeSpots()) {
                // Loop through all cells and make a new board.
                Board newBoard = new Board(board);
                Cell newCell = new Cell(cell.getX(), cell.getY());
                newCell.setOccupant(new TicTacToeActor(Actor.Type.TYPE_1));
                newBoard.setCell(cell.getX(), cell.getY(), newCell);

                System.out.println(newBoard.getCell(cell.getX(), cell.getY()));
                int value = minimax(newBoard, depth - 1, !isMaximizing);
                bestVal = Math.min(bestVal, value);

                return bestVal;
            }
        } else {
            int bestVal = Integer.MIN_VALUE;
            for (Cell cell : game.getBoard().getFreeSpots()) {
                Board newBoard = new Board(board);
                Cell newCell = new Cell(cell.getX(), cell.getY());
                newCell.setOccupant(new TicTacToeActor(Actor.Type.TYPE_2));
                newBoard.setCell(cell.getX(), cell.getY(), newCell);
                int value = minimax(newBoard, depth - 1, !isMaximizing);
                bestVal = Math.max(bestVal, value);

                return bestVal;
            }
        }

        throw new IllegalStateException();
    }
}
