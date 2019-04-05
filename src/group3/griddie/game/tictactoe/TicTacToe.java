package group3.griddie.game.tictactoe;

import group3.griddie.controller.board.BoardController;
import group3.griddie.game.Game;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.view.View;
import group3.griddie.view.board.tictactoe.TicTacToeBoardView;

public class TicTacToe extends Game {

    public TicTacToe() {

    }

    @Override
    public boolean onPlayerMove(Player player, int column, int row) {
        Cell cell = getBoard().getCell(column, row);

        if (cell.isDisabled()) {
            return false;
        }

        int index = getPlayers().indexOf(player);

        TicTacToeActor actor = new TicTacToeActor(
                index % 2 == 0 ? TicTacToeActor.Type.O : TicTacToeActor.Type.X,
                column,
                row
        );

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
    protected View<BoardController> createBoardView(Board board) {
        return new TicTacToeBoardView(board);
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

    private void checkIfDiagonalWon(Game game, Board board) {
        Cell cell       = board.getCell(1, 1);
        Cell topLeft    = board.getCell(0, 0);
        Cell topRight   = board.getCell(2, 0);
        Cell botLeft    = board.getCell(0, 2);
        Cell botRight   = board.getCell(2, 2);

        TicTacToeActor actor            = (TicTacToeActor) cell.getOccupant();
        TicTacToeActor topLeftActor     = (TicTacToeActor) topLeft.getOccupant();
        TicTacToeActor botRightActor    = (TicTacToeActor) botRight.getOccupant();

        if (actor == null) {
            return;
        }

        if (topLeftActor != null) {
            if (actor.getType() == topLeftActor.getType()) {
                if (botRightActor != null) {
                    if (actor.getType() == botRightActor.getType()) {
                        System.out.println("Diagonal won!");
                        game.stop();
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
                        System.out.println("Diagonal won!");
                        game.stop();
                    }
                }
            }
        }
    }
}
