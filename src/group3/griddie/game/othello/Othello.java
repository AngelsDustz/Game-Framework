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

        this.addPlayer(new HumanPlayer(this, Actor.Type.TYPE_1, "Player 1"));

        AIPlayer aiPlayer = new AIPlayer(this, Actor.Type.TYPE_2, "AI Player");
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

        player.registerActor(actor);

        cell.setOccupant(actor);
        cell.setDisabled(true);

        return true;
    }

    private ArrayList<Cell> getMoveCells(Player player, Cell toCell) {
        // get all empty spots.
        ArrayList<Cell> empty   = getBoard().getSpotsByActorType(player.getActorType());
        ArrayList<Cell> moves   = null;

        for (Cell cell : empty) {
            // check if we have an actor in any of the directons.
            Cell[] colliding = getBoard().getAdjacentCells(cell);

            for (int i=0;i<colliding.length;i++) {
                ArrayList<Cell> potentialMoves = new ArrayList<>();

                if (colliding[i] == null || !colliding[i].isOccupied()) {
                    // Out of bounds.
                    continue;
                }

                if (colliding[i].getOccupant().getType() == player.getActorType()) {
                    // We cant beat our own cells.
                    continue;
                }

                int dirX = -1 + (i%3);
                int dirY = -1 + (i/3);

                // follow actor if we do. follow untill we reach empty or null.
                Cell checkCell      = getBoard().getCell(cell.getX()-dirX, cell.getY()-dirY);
                boolean finished    = false;

                while (!finished) {
//                    System.out.println("Checking cell: "+checkCell);
                    if (checkCell == null) {
                        // If we reached out of bounds continue.
                        finished = true;
                        continue;
                    }

                    if (!checkCell.isDisabled() || !checkCell.isOccupied() || checkCell.getOccupant() == null) {
                        // Empty spot.
                        System.out.println("Found unoccupied cell: "+checkCell);
                        potentialMoves.add(checkCell);

                        System.out.println("Checking if :");
                        System.out.println(checkCell);
                        System.out.println("Is equal to :");
                        System.out.println(toCell);

                        if (checkCell.equals(toCell)) {
                            moves = potentialMoves;
                        }

                        finished = true;
                        continue;
                    }

                    if (checkCell.getOccupant().getType() != player.getActorType()) {
                        potentialMoves.add(checkCell);
                        // We found a valid move, follow it till the end of hell.
                        checkCell = getBoard().getCell(checkCell.getX()-dirX, checkCell.getY()-dirY);
                        continue;
                    }
                }
            }
        }

        return moves;
    }

    public ArrayList<Cell> getLegalMoves(Board board, Actor.Type type) {
        // get all empty spots.
        ArrayList<Cell> empty       = board.getFreeSpots();
        ArrayList<Cell> legalMoves  = new ArrayList<>();

        for (Cell cell : empty) {
            // check if we have an actor in any of the directons.
            Cell[] colliding = board.getAdjacentCells(cell);

            for (int i=0;i<colliding.length;i++) {
                if (colliding[i] == null || !colliding[i].isOccupied()) {
                    // Out of bounds.
                    continue;
                }

                if (colliding[i].getOccupant().getType() == type) {
                    // We cant beat our own cells.
                    continue;
                }

                int dirX = -1 + (i%3);
                int dirY = -1 + (i/3);

                System.out.println("["+i+"] Found actor: "+colliding[i].getOccupant().getType()+" at "+colliding[i]);
                System.out.println("["+i+"] Direction: X:"+dirX+" Y:"+dirY);

                // follow actor if we do. follow untill we reach empty or null.
                Cell checkCell      = board.getCell(cell.getX()-dirX, cell.getY()-dirY);
                boolean finished    = false;

                while (!finished) {
                    System.out.println("Checking cell: "+checkCell);
                    if (checkCell == null) {
                        // If we reached out of bounds continue.
                        finished = true;
                        continue;
                    }

                    if (!checkCell.isDisabled() || !checkCell.isOccupied() || checkCell.getOccupant() == null) {
                        // Empty spot.
//                        System.out.println("Found unoccupied cell: "+cell);
                        legalMoves.add(checkCell);
                        finished = true;
                        continue;
                    }

                    if (checkCell.getOccupant().getType() != type) {
//                        System.out.println("Found enemy piece!");
                        // We found a valid move, follow it till the end of hell.
                        checkCell = board.getCell(checkCell.getX()-dirX, checkCell.getY()-dirY);
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
    protected View<Board> createBoardView(Board board) {
        return new OthelloBoardView(board);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onStart() {
        getBoard().getCell(3,3).setOccupant(new OthelloActor(OthelloActor.Type.TYPE_1));
        getBoard().getCell(4,4).setOccupant(new OthelloActor(OthelloActor.Type.TYPE_1));
        getBoard().getCell(4,3).setOccupant(new OthelloActor(OthelloActor.Type.TYPE_2));
        getBoard().getCell(3,4).setOccupant(new OthelloActor(OthelloActor.Type.TYPE_2));
    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onTick() {
    }
}
