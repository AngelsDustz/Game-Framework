package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.game.Move;
import group3.griddie.game.ai.OthelloAI;
import group3.griddie.game.ai.TicTacToeAI;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.TicTacToeActor;

import java.util.Random;

public class TicTacToe extends Game {


    public TicTacToe(String game) {
        super(game);
    }

    @Override
    public boolean moveIsValid(Player player, int x, int y) {
        Cell cell = getBoard().getCell(x, y);

        try {
            boolean test = !cell.isOccupied();
        } catch (Exception e) {
            System.out.println("ERROR X: " + x + " Y:" + y);
        }

        return !cell.isOccupied();
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onTick() {

    }

    @Override
    public void onPlayerMove(Player player, int x, int y) {
        getBoard().getCell(x, y).setOccupant(new TicTacToeActor(player.getActorType()));
    }

    @Override
    protected Board createBoard() {
        return new Board(3, 3);
    }

    @Override
    public boolean checkWin(Move move) {
        Cell[][] cells = getBoard().getCells();

        return horizontalWin(move, cells) || verticalWin(move, cells) || diagonalWin(move, cells);
    }

    private boolean horizontalWin(Move move, Cell[][] cells) {
        for (int i = 0; i < 3; i++) {
            Cell cell = cells[i][move.getY()];

            if (!cell.isOccupied() || cell.getOccupant().getType() != move.getPlayer().getActorType()) {
                return false;
            }
        }

        System.out.println("HORIZONTAL WIN");

        return  true;
    }

    private boolean verticalWin(Move move, Cell[][] cells) {
        for (int i = 0; i < 3; i++) {
            Cell cell = cells[move.getX()][i];

            if (!cell.isOccupied() ||cell.getOccupant().getType() != move.getPlayer().getActorType()) {
                return false;
            }
        }

        System.out.println("VERTICAL WIN");

        return  true;
    }

    private boolean diagonalWin(Move move, Cell[][] cells) {
        if (move.getX() % 2 == 1 || move.getY() % 2 == 1 || !cells[1][1].isOccupied()) {
            return false;
        }

        //TODO

        return false;
    }

    @Override
    public AIPlayer createAiPlayer() {
        AIPlayer aiPlayer = new AIPlayer("AI Player" + new Random().nextInt(), AIPlayer.Difficulty.DIFFICULTY_HARD);
        aiPlayer.setGameAI(new TicTacToeAI(this, aiPlayer));
        return aiPlayer;
    }

    @Override
    public boolean canDoTurn(Player player) {
        if (this.getBoard().getFreeSpots().size() > 0) {
            return true;
        }

        return false;
    }

    public Actor.Type checkIfWon(Board board) {
        Actor.Type check = checkIfColumnWon(board);
        if (check != null) return check;

        check = checkIfRowWon(board);
        if (check != null) return check;

        check = checkIfDiagonalWon(board);
        if (check != null) return check;

        return null;
    }

    public Actor.Type checkIfColumnWon(Board board) {
        for (int i=0;i<board.getWidth();i++) {
            Cell toCheck            = board.getCell(i, 0);
            TicTacToeActor toActor  = (TicTacToeActor) toCheck.getOccupant();
            boolean check           = true;
            if (toActor == null) {
                continue;
            }

            for (int c=1;c<board.getHeight();c++) {
                Cell nextCheck              = board.getCell(i, c);
                TicTacToeActor nextActor    = (TicTacToeActor) nextCheck.getOccupant();
                if (nextActor == null) {
                    check = false;
                    continue;
                }

                if (toActor.getType() != nextActor.getType()) {
                    check = false;
                }
            }

            if (check) {
                return toActor.getType();
            }
        }

        return null;
    }

    private Actor.Type checkIfRowWon(Board board) {
        for (int i=0;i<board.getWidth();i++) {
            Cell toCheck            = board.getCell(0, i);
            TicTacToeActor toActor  = (TicTacToeActor) toCheck.getOccupant();
            boolean check           = true;
            if (toActor == null) {
                continue;
            }

            for (int c=1;c<board.getHeight();c++) {
                Cell nextCheck              = board.getCell(c, i);
                TicTacToeActor nextActor    = (TicTacToeActor) nextCheck.getOccupant();
                if (nextActor == null) {
                    check = false;
                    continue;
                }

                if (toActor.getType() != nextActor.getType()) {
                    check = false;
                }
            }

            if (check) {
                return toActor.getType();
            }
        }

        return null;
    }

    private Actor.Type checkIfDiagonalWon(Board board) {
        Cell cell       = board.getCell(1, 1);
        Cell topLeft    = board.getCell(0, 0);
        Cell topRight   = board.getCell(2, 0);
        Cell botLeft    = board.getCell(0, 2);
        Cell botRight   = board.getCell(2, 2);

        TicTacToeActor actor            = (TicTacToeActor) cell.getOccupant();
        TicTacToeActor topLeftActor     = (TicTacToeActor) topLeft.getOccupant();
        TicTacToeActor botRightActor    = (TicTacToeActor) botRight.getOccupant();

        if (actor == null) {
            return null;
        }

        if (topLeftActor != null) {
            if (actor.getType() == topLeftActor.getType()) {
                if (botRightActor != null) {
                    if (actor.getType() == botRightActor.getType()) {
                        return actor.getType();
                    }
                }
            }
        }

        TicTacToeActor topRightActor    = (TicTacToeActor) topRight.getOccupant();
        TicTacToeActor botLeftActor     = (TicTacToeActor) botLeft.getOccupant();

        if (topRightActor != null) {
            if (actor.getType() == topRightActor.getType()) {
                if (botLeftActor != null) {
                    if (actor.getType() == botLeftActor.getType()) {
                        return actor.getType();
                    }
                }
            }
        }

        return null;
    }
}
