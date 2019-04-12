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

    private int[][] gridValues = {
            {300, -30, 100, 80, 80, 100, -30, 300},
            {-30, -500, -45, -50, -50, -45, -500, -30},
            {100, -45, 3, 1, 1, 3, -45, 100},
            {80, -50, 1, 5, 5, 1, -50, 80},
            {80, -50, 1, 5, 5, 1, -50, 80},
            {100, -45, 3, 1, 1, 3, -45, 100},
            {-30, -500, -45, -50, -50, -45, -500, -30},
            {300, -30, 100, 80, 80, 100, -30, 300}
    };

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
            long start      = System.currentTimeMillis();
            Cell smartCell  = this.pickSmartCell();
            long end        = System.currentTimeMillis();

            System.out.println("Found move: "+smartCell+" in "+(end-start)+ " ms.");

            if (smartCell == null) {
                System.out.println("No moves found! Valid moves: "+this.game.getLegalMoves(game.getBoard(), player.getActorType()));
                return null;
            }

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
        int lastMaxBest         = 0;
        int lastMinBest         = 0;

        if (type != null) {
            if (type != this.player.getActorType()) {
                // We won.
                return 1000 - (depth*5) - lastMaxBest - (game.getCountByActor(newBoard, enemyType)/10);
//                return 100 - (depth*5) - game.getCountByActor(newBoard, enemyType);
            } else {
                return -1000 + (depth*5) + lastMinBest + (game.getCountByActor(newBoard, this.player.getActorType())/10);
//                return -100 + (depth*5) + game.getCountByActor(newBoard, this.player.getActorType());
            }
        }

        if (depth >= 6) {
            if (isMax) {
                return 1000 - (depth*5) - lastMaxBest - (game.getCountByActor(newBoard, enemyType)/10);
            } else {
                return -1000 + (depth*5) + lastMinBest + (game.getCountByActor(newBoard, this.player.getActorType())/10);
            }
        }

        if (isMax) {
            int best = Integer.MIN_VALUE;

            for (Cell cell : game.getLegalMoves(newBoard, this.player.getActorType())) {
                cell.testOccupant(new OthelloActor(this.player.getActorType()));

                int newBest = Integer.max(best, minimax(newBoard, depth + 1, false));
                newBest += this.gridValues[cell.getX()][cell.getY()];

                cell.testOccupant(null);

                if (newBest < best) {
                    return newBest;
                } else {
                    lastMaxBest = best = newBest;
                }
            }

            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for (Cell cell : game.getLegalMoves(newBoard, enemyType)) {
                cell.testOccupant(new OthelloActor(enemyType));

                int newBest = Integer.min(best, minimax(newBoard, depth+1, true));
                newBest += this.gridValues[cell.getX()][cell.getY()];

                cell.testOccupant(null);

                if (newBest > best) {
                    return newBest;
                } else {
                    lastMinBest = best = newBest;
                }
            }

            return best;
        }
    }
}
