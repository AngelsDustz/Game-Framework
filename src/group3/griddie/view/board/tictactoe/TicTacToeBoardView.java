package group3.griddie.view.board.tictactoe;

import group3.griddie.controller.board.BoardController;
import group3.griddie.controller.board.CellController;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.view.RootView;
import group3.griddie.view.board.CellView;
import javafx.scene.layout.GridPane;

import java.util.Observable;
import java.util.Observer;

public class TicTacToeBoardView extends RootView<BoardController> implements Observer {

    public TicTacToeBoardView(Board board) {
        super(board, new GridPane());
        board.addObserver(this);
    }

    @Override
    public void initializeView() {
        Board board = (Board) getModel();
        GridPane root = (GridPane) getParent();

        for (int c = 0; c < board.getHeight(); c++) {
            for (int r = 0; r < board.getWidth(); r++) {
                Cell cell = board.getCell(c, r);

                CellView cellView = new CellView(cell, new TicTacToeActorView(null));
                cellView.init();

                cellView.setController(new CellController(cell));

                root.add(cellView.getNode(), c, r);
            }
        }
    }

    @Override
    public void initializeControls() {

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof Board) {
            //TODO
        }
    }
}
