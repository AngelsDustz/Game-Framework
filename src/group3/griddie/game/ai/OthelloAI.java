package group3.griddie.game.ai;

import group3.griddie.game.Game;
import group3.griddie.game.othello.Othello;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.OthelloActor;

import java.util.ArrayList;
import java.util.Random;

public class OthelloAI implements AI {
    private Othello     game;
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
        ArrayList<Cell> moves = game.getLegalMoves(game.getBoard(), player.getActorType());

        if (this.player.getDifficulty() == AIPlayer.Difficulty.DIFFICULTY_HARD) {
            long start = System.currentTimeMillis();
            Cell smartCell = this.pickSmartCell();
            long end = System.currentTimeMillis();

            System.out.println("Found move in "+(end-start)+ " ms.");

            return smartCell;
        }

        return moves.get(new Random().nextInt(moves.size()));
    }

    private Cell pickSmartCell() {
        int bestMoveValue   = Integer.MIN_VALUE;
        Cell move           = null;

        for (Cell cell : this.game.getLegalMoves(this.game.getBoard(), this.player.getActorType())) {
            if (cell.getOccupant() == null) {
                cell.testOccupant(new OthelloActor(this.player.getActorType()));

                int value = minimax(this.game.getBoard(), 0, false);

                cell.testOccupant(null);

                if (value > bestMoveValue) {
                    System.out.println("Found a better move: "+cell+ " with score "+value);
                    bestMoveValue = value;
                    move = cell;
                }
            }
        }



        return move;
    }

    private int minimax(Board board, int depth, boolean isMax) {
        Board newBoard          = new Board(board); // Hardcopy.
        Actor.Type type         = game.checkIfWon(newBoard);
        Actor.Type enemyType    = (this.player.getActorType() == Actor.Type.TYPE_1)? Actor.Type.TYPE_2 : Actor.Type.TYPE_1;

        if (type != null) {
            if (type != this.player.getActorType()) {
                // We won.
                return 100 - depth - game.getCountByActor(newBoard, enemyType);
            } else {
                return -100 + depth + game.getCountByActor(newBoard, this.player.getActorType());
            }
        }

        if (depth > 4) {
            if (isMax) {
                return 100 - depth - game.getCountByActor(newBoard, enemyType);
            } else {
                return -100 + depth + game.getCountByActor(newBoard, this.player.getActorType());
            }
        }

        if (isMax) {
            int best = Integer.MIN_VALUE;

            for (Cell cell : game.getLegalMoves(newBoard, this.player.getActorType())) {
                cell.testOccupant(new OthelloActor(this.player.getActorType()));

                best = Integer.max(best, minimax(newBoard, depth+1, false));

                cell.testOccupant(null);
            }

            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for (Cell cell : game.getLegalMoves(newBoard, enemyType)) {
                cell.testOccupant(new OthelloActor(enemyType));

                best = Integer.min(best, minimax(newBoard, depth+1, true));

                cell.testOccupant(null);
            }

            return best;
        }
    }
}
