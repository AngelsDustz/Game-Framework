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

        int index = getPlayers().indexOf(player);


        OthelloActor actor = new OthelloActor(player.getActorType());

        player.registerActor(actor);

        cell.setOccupant(actor);
        cell.setDisabled(true);

        return true;
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

    public ArrayList<Cell> getLegalMoves(Board board, Actor.Type type) {
        ArrayList<Cell> cells = new ArrayList<>();

        for (int row=0;row<board.getHeight();row++) {
            for (int col=0;col<board.getWidth();col++) {
                Cell cell = board.getCell(col, row);

                if (!cell.isOccupied()) {
                    continue;
                }

                if (cell.getOccupant().getType() == type) {
                    // We can never beat our own piece.
                    continue;
                }

//                System.out.println("Occupied cell: "+cell);

                for (int i=0;i<3;i++) {
                    for (int c=0;c<3;c++) {
                        if (c == 1 && i == 1) {
                            // Skip the piece we are checking for.
                            continue;
                        }

                        Cell cellToCheck = board.getCell(col-(c-1), row-(i-1));

                        if (cellToCheck == null) {
                            continue;
                        }

                        if (cellToCheck.isOccupied()) {
                            continue;
                        }

//                        System.out.println("Cell to check: "+cellToCheck);

                        Cell move = board.getCell(col-((c-1)*-1), row-((i-1)*-1));

                        if (move == null) {
                            continue;
                        }

                        if (!move.isOccupied()) {
                            continue;
                        }

                        if (move.getOccupant().getType() == type) {
//                            System.out.println("Valid move: "+cellToCheck);
                            cells.add(cellToCheck);
                        }
                    }
                }

                // Currently cells are all valid directions we can go to.
            }
        }

        return cells;
    }
}
