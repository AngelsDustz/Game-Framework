package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.player.Player;
import group3.griddie.game.player.RemotePlayer;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.TicTacToeActor;

public class TicTacToe extends Game {


    public TicTacToe(String game) {
        super(game);

    }

    @Override
    public boolean onPlayerMove(Player player, int column, int row) {
        Cell cell = getBoard().getCell(column, row);

        if (cell.isDisabled()) {
            return false;
        }

        TicTacToeActor actor = new TicTacToeActor(player.getActorType());

        player.registerActor(actor);

        cell.setOccupant(actor);
        cell.setDisabled(true);

        return true;
    }

    @Override
    protected Board createBoard() {
        return new Board(3, 3);
    }

    @Override
    protected void onInit() {
        HumanPlayer human = new HumanPlayer();
        human.setName("HUMAN");

        RemotePlayer remotePlayer = new RemotePlayer();
        remotePlayer.setName("REMOTE");

        lobby.join(human);
        lobby.join(remotePlayer);
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onTick() {
        Board board = this.getBoard();

        if (this.checkIfWon(board) != null) {
            this.stop();
        }
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
