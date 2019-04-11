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
        this.addPlayer(new HumanPlayer(this, Actor.Type.TYPE_1, "Player 1"));

//        AIPlayer aiPlayer = new AIPlayer(this, Actor.Type.TYPE_1, "AI Player");
//        aiPlayer.setDifficulty(AIPlayer.Difficulty.DIFFICULTY_HARD);
//        aiPlayer.setGameAI(new OthelloAI(this, aiPlayer));

//        this.addPlayer(aiPlayer);
    }

    private void updateCellValidity(Board board, Actor.Type type) {
        for (Cell cell: board.getCellsArray()) {
            cell.setValidSpot(false);
        }

        for (Cell cell: this.getLegalMoves(board, type)) {
            cell.setValidSpot(true);
        }
    }

    @Override
    public boolean onPlayerMove(Player player, int column, int row) {
        Cell cell = getBoard().getCell(column, row);
        System.out.println(this.getLegalMoves(this.getBoard(), player.getActorType()));


        if (cell.isDisabled()) {
            return false;
        }


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

            System.out.println(adjacentCell);
            updates.add(adjacentCell);

            Cell following = this.getBoard().getCell(adjacentCell.getX()+dirX, adjacentCell.getY()+dirY);

            if (following == null) {
                continue;
            }

            if (!following.isOccupied()) {
                continue;
            }

            boolean finished = false;

            while (!finished) {
                System.out.println(following);
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
                        finished = true;
                        continue;
                    }
                } else {
                    finished = true;
                    continue;
                }

                following = this.getBoard().getCell(following.getX()-dirX, following.getY()-dirY);
            }

            for (Cell c : updates) {
                System.out.println("Updating: "+c);

                player.registerActor(actor);
                c.setOccupant(actor);
                c.setDisabled(true);
            }

        }


        player.registerActor(actor);

        cell.setOccupant(actor);
        cell.setDisabled(true);

        this.updateCellValidity(this.getBoard(), (player.getActorType() == Actor.Type.TYPE_1)? Actor.Type.TYPE_2 : Actor.Type.TYPE_1);

        return true;
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

                if (following.isOccupied() && following.getOccupant().getType() != type) {
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
