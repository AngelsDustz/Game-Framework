package group3.griddie.game.ortello;

import group3.griddie.controller.board.BoardController;
import group3.griddie.game.Game;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Board;
import group3.griddie.view.View;
import group3.griddie.view.board.ortello.OrtelloBoardView;

public class Ortello extends Game {

    @Override
    public boolean onPlayerMove(Player player, int column, int row) {
        return false;
    }

    @Override
    protected Board createBoard() {
        return new Board(8,8);
    }

    @Override
    protected View<BoardController> createBoardView(Board board) {
        return new OrtelloBoardView(board);
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
}
