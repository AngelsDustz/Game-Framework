package group3.griddie.viewOLD.board.othello;

import group3.griddie.controllerOLD.board.CellController;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.viewOLD.RootViewOLD;
import group3.griddie.viewOLD.board.CellViewOLD;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Observable;
import java.util.Observer;

public class OthelloBoardViewOLD extends RootViewOLD<Board> implements Observer {

    public OthelloBoardViewOLD(Board board) {
        super(board, new StackPane());
    }

    @Override
    public void initializeView() {
        StackPane root = (StackPane) getParent();

        Board board = (Board) getModel();
        GridPane grid = new GridPane();

        for (int c = 0; c < board.getHeight(); c++) {
            for (int r = 0; r < board.getWidth(); r++) {
                Cell cell = board.getCell(c, r);

                CellViewOLD cellView = new CellViewOLD(cell, new OthelloActorViewOLD(null));
                cellView.init();

                cellView.setController(new CellController(cell));

                grid.add(cellView.getNode(), c, r);
            }
        }

        root.getChildren().add(grid);
    }

    @Override
    public void initializeControls() {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
