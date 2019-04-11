package group3.griddie.game.othello;

import group3.griddie.game.Game;
import group3.griddie.game.ai.OthelloAI;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.OthelloActor;
import group3.griddie.view.View;
import group3.griddie.view.board.othello.OthelloBoardView;

import java.util.ArrayList;

public class Othello extends Game {

    public Othello() {
        super("Othello");

        this.addPlayer(new HumanPlayer(this, Actor.Type.TYPE_2, "Player 1"));

        AIPlayer aiPlayer = new AIPlayer(this, Actor.Type.TYPE_1, "AI Player");
        aiPlayer.setDifficulty(AIPlayer.Difficulty.DIFFICULTY_HARD);
        aiPlayer.setGameAI(new OthelloAI(this, aiPlayer));

        this.addPlayer(aiPlayer);
    }

    @Override
    public boolean onPlayerMove(Player player, int column, int row) {
        Cell cell = getBoard().getCell(column, row);

        if (cell.isDisabled()) {
            return false;
        }

        OthelloActor actor = new OthelloActor(player.getActorType());

        Cell[] adjacent = this.getBoard().getAdjacentCells(cell);
        for (int i=0;i<adjacent.length;i++) {
            Cell adjacentCell       = adjacent[i];
            boolean finished        = false;
            ArrayList<Cell> updates = new ArrayList<>();

            int dirX = -1 + (i%3);
            int dirY = -1 + (i/3);

            while (!finished) {
                if (dirX == 0 && dirY == 0) {
                    finished = true;
                }

                if (adjacentCell == null) {
                    finished = true;
                    continue;
                }

                if (adjacentCell.isOccupied()) {
                    if (adjacentCell.getOccupant().getType() != player.getActorType()) {
                        updates.add(adjacentCell);

                        adjacentCell = this.getBoard().getCell(adjacentCell.getX()+dirX, adjacentCell.getY()+dirY);
                        continue;
                    }
                }

                adjacentCell = this.getBoard().getCell(adjacentCell.getX()+dirX, adjacentCell.getY()+dirY);
            }

            for (Cell c : updates) {
                player.registerActor(actor);
                c.setOccupant(actor);
                c.setDisabled(true);
            }
        }

        player.registerActor(actor);

        cell.setOccupant(actor);
        cell.setDisabled(true);

        return true;
    }

    public ArrayList<Cell> getLegalMoves(Board board, Actor.Type type) {
        ArrayList<Cell> legalMoves = new ArrayList<>();

        for (Cell cell : board.getSpotsByActorType(type)) {
            Cell[] colliding = board.getAdjacentCells(cell);

            for (int i=0;i<colliding.length;i++) {
                Cell spot = colliding[i];

                if (spot == null) {
                    // Out of bounds.
                    continue;
                }

                if (spot.isOccupied() && spot.getOccupant().getType() != type) {
                    // Found an enemy piece, follow it till the end.
                    int dirX = -1 + (i%3);
                    int dirY = -1 + (i/3);

                    Cell following = board.getCell(cell.getX()+dirX, cell.getY()+dirY);
                    boolean finished = false;

                    while (!finished) {
                        if (following == null) {
                            finished = true;
                            continue;
                        }

                        if (following.isOccupied()) {
                            if (following.getOccupant().getType() == type) {
                                following = board.getCell(following.getX()-dirX, following.getY()-dirY);
                                continue;
                            }
                        }

                        if (following.getOccupant() == null) {
                            legalMoves.add(following);
                            finished = true;
                            continue;
                        }

                        following = board.getCell(following.getX()-dirX, following.getY()-dirY);
                    }
                }

            }
        }

        return legalMoves;
    }

    @Override
    protected Board createBoard() {
        return new Board(8,8);
    }

    @Override
    protected View createBoardView(Board board) {
        return new OthelloBoardView(board);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onStart() {
        getBoard().getCell(3,3).setOccupant(new OthelloActor(OthelloActor.Type.TYPE_2));
        getBoard().getCell(4,4).setOccupant(new OthelloActor(OthelloActor.Type.TYPE_2));
        getBoard().getCell(4,3).setOccupant(new OthelloActor(OthelloActor.Type.TYPE_1));
        getBoard().getCell(3,4).setOccupant(new OthelloActor(OthelloActor.Type.TYPE_1));
    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onTick() {
    }
}
