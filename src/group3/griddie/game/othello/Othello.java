package group3.griddie.game.othello;

import group3.griddie.Griddie;
import group3.griddie.game.Game;
import group3.griddie.game.Move;
import group3.griddie.game.ai.OthelloAI;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.OthelloActor;

import java.util.ArrayList;
import java.util.Random;

public class Othello extends Game {

    public Othello() {
        super("Reversi");
    }

    private void updateCellValidity(Board board, Actor.Type type) {
        for (Cell cell: board.getCellsArray()) {
            cell.setValidSpot(false);
        }

        type = (type == Actor.Type.TYPE_1)? Actor.Type.TYPE_2 : Actor.Type.TYPE_1;
        for (Cell cell: this.getLegalMoves(board, type)) {
            cell.setValidSpot(true);
        }
    }

    @Override
    public boolean moveIsValid(Player player, int x, int y) {
        Cell cell = this.getBoard().getCell(x, y);

        if (cell.isOccupied()) {
            return false;
        }

        for (Cell c : this.getLegalMoves(this.getBoard(), player.getActorType())) {
            if (c.getX() == cell.getX()) {
                if (c.getY() == cell.getY()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean checkWin(Move move) {
        return false;
    }

    @Override
    public void onPlayerMove(Player player, int column, int row) {
        Cell cell           = getBoard().getCell(column, row);
        OthelloActor actor  = new OthelloActor(player.getActorType());
        Cell[] adjacent     = this.getBoard().getAdjacentCells(cell);

        for (int i=0;i<adjacent.length;i++) {
            Cell adjacentCell       = adjacent[i];
            ArrayList<Cell> updates = new ArrayList<>();


            int dirX = -1 + (i%3);
            int dirY = -1 + (i/3);

            if (adjacentCell == null) {
                continue;
            }

            if (!adjacentCell.isOccupied()) {
                continue;
            }

            if (adjacentCell.getOccupant().getType() == player.getActorType() || adjacentCell.getOccupant().getType() == null) {
                continue;
            }

//            System.out.println(adjacentCell);
            updates.add(adjacentCell);

            Cell following = this.getBoard().getCell(adjacentCell.getX()+dirX, adjacentCell.getY()+dirY);

            if (following == null) {
                continue;
            }

            if (!following.isOccupied()) {
                continue;
            }

            boolean finished    = false;
            boolean revalid     = false;

            while (!finished) {
//                System.out.println(following);
                if (following == null) {
                    finished = true;
                    continue;
                }

                if (following.isOccupied()) {
                    if (following.getOccupant().getType() == null) {
                        finished = true;
                        continue;
                    }

                    if (following.getOccupant().getType() != player.getActorType()) {
                        updates.add(following);
                        following = this.getBoard().getCell(following.getX()+dirX, following.getY()+dirY);
                        continue;
                    }

                    if (following.getOccupant().getType() == player.getActorType()) {
                        revalid = true;
                        finished = true;
                        continue;
                    }
                } else {
                    finished = true;
                    continue;
                }

                following = this.getBoard().getCell(following.getX()-dirX, following.getY()-dirY);
            }

            if (revalid) {
                for (Cell c : updates) {
//                System.out.println("Updating: "+c);

                    player.registerActor(actor);
                    c.setOccupant(actor);
                    c.setDisabled(true);
                }
            }

        }


        player.registerActor(actor);

        cell.setOccupant(actor);
        cell.setDisabled(true);

        this.updateCellValidity(this.getBoard(), player.getActorType());

        //return true;
    }

    public ArrayList<Cell> getLegalMoves(Board board, Actor.Type type) {
        ArrayList<Cell> legalMoves  = new ArrayList<>();

        for (Cell cell : board.getSpotsByActorType(type)) {
            Cell[] colliding = board.getAdjacentCells(cell);

            if (!cell.isOccupied()) {
                continue;
            }

            for (int i=0;i<colliding.length;i++) {
                Cell spot = colliding[i];

                if (spot == null) {
                    // Out of bounds.
                    continue;
                }

                if (!spot.isOccupied()) {
                    continue;
                }

                if (spot.getOccupant().getType() == type || spot.getOccupant().getType() == null) {
                    continue;
                }

                int dirX = -1 + (i%3);
                int dirY = -1 + (i/3);

                Cell following = board.getCell(spot.getX()+dirX, spot.getY()+dirY);

                if (following == null) {
                    continue;
                }

                if (following.isOccupied() && following.getOccupant().getType() == type) {
                    continue;
                }

                boolean finished = false;

                while (!finished) {
                    if (following == null) {
                        finished = true;
                        continue;
                    }

                    if (following.isOccupied()) {
                        if (following.getOccupant().getType() != type) {
                            following = board.getCell(following.getX()+dirX, following.getY()+dirY);
                            continue;
                        }

                        if (following.getOccupant().getType() == type) {
                            finished = true;
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

        return legalMoves;
    }

    public Actor.Type checkIfWon(Board board) {
        if (this.getLegalMoves(board, Actor.Type.TYPE_1).size() > 0) {
            return null;
        }

        if (this.getLegalMoves(board, Actor.Type.TYPE_2).size() > 0) {
            return null;
        }

        int actorOneScore = this.getCountByActor(board, Actor.Type.TYPE_1);
        int actorTwoScore = this.getCountByActor(board, Actor.Type.TYPE_2);

        if (actorOneScore == actorTwoScore) {
            return null;
        }

        if (actorOneScore > actorTwoScore) {
            return Actor.Type.TYPE_1;
        } else {
            return Actor.Type.TYPE_2;
        }
    }

    public int getCountByActor(Board board, Actor.Type type) {
        int count = 0;

        for (Cell cell : board.getCellsArray()) {
            if (cell.isOccupied() && cell.getOccupant().getType() == type) {
                count++;
            }
        }

        return count;
    }

    @Override
    protected Board createBoard() {
        return new Board(8,8);
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

        this.updateCellValidity(this.getBoard(), Actor.Type.TYPE_2);
    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onTick() {
        if (this.getLegalMoves(this.getBoard(), Actor.Type.TYPE_1).size() == 0) {
            if (this.getLegalMoves(this.getBoard(), Actor.Type.TYPE_2).size() == 0) {
                System.out.println("Game ended.");
                this.stop();
            }
        }
    }

    @Override
    protected AIPlayer createAiPlayer() {
        AIPlayer aiPlayer = new AIPlayer("B3", AIPlayer.Difficulty.DIFFICULTY_HARD);
        aiPlayer.setGameAI(new OthelloAI(this, aiPlayer));
        return aiPlayer;
    }

    @Override
    public boolean canDoTurn(Player player) {
        if (this.getLegalMoves(this.getBoard(), player.getActorType()).size() == 0) {
            return false;
        }

        return true;
    }
}
