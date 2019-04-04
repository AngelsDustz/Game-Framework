package group3.griddie.game.tictactoe;

import group3.griddie.controller.board.BoardController;
import group3.griddie.game.Game;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.view.View;
import group3.griddie.view.board.tictactoe.TicTacToeBoardView;

public class TicTacToe extends Game {
    public TicTacToe() {
        super();

        AIPlayer aiPlayer = new AIPlayer(this, "AI Player");
        this.addPlayer(aiPlayer);
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

    /**
     * Win situations:
     * 789
     */
    @Override
    protected void onTick() {
        Board board = this.getBoard();

        this.checkIfColumnWon(this, board);
        this.checkIfRowWon(this, board);
        this.checkIfDiagonalWon(this, board);
    }

    private void checkIfColumnWon(Game game, Board board) {
        boolean won = false;

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
                won = true;
            }
        }

        if (won) {
            System.out.println("Column win!");
            game.stop();
        }
    }

    private void checkIfRowWon(Game game, Board board) {
        boolean won = false;

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
                won = true;
            }
        }

        if (won) {
            System.out.println("Row win!");
            game.stop();
        }
    }

    private void checkIfDiagonalWon(Game game, Board board) {
        // todo
    }
}
