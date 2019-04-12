package group3.griddie.viewOLD.board.tictactoe;

import group3.griddie.controllerOLD.board.CellController;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.viewOLD.RootViewOLD;
import group3.griddie.viewOLD.board.CellViewOLD;
import javafx.scene.layout.GridPane;

import java.util.Observable;
import java.util.Observer;

public class TicTacToeBoardViewOLD extends RootViewOLD<Board> implements Observer {

    public TicTacToeBoardViewOLD(Board board) {
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

                CellViewOLD cellView = new CellViewOLD(cell, new TicTacToeActorViewOLD(null));
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
