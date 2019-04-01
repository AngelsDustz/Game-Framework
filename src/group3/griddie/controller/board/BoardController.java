package group3.griddie.controller.board;

import group3.griddie.controller.Controller;
import group3.griddie.model.board.Board;
import group3.griddie.view.View;
import group3.griddie.view.board.BoardView;

public class BoardController extends Controller {

    public BoardController(Board board) {
        super(board);
    }

    @Override
    protected View createView() {
        return new BoardView(this, (Board) getModel());
    }
}
