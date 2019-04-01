package group3.griddie.view.board;

import group3.griddie.controller.board.BoardController;
import group3.griddie.model.board.Board;
import group3.griddie.view.View;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class BoardView extends View {

    public BoardView(BoardController controller, Board board) {
        super(controller, board, new GridPane());
    }

    @Override
    public void initializeView() {

    }

    @Override
    public void initializeControls() {

    }
}
