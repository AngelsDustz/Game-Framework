package group3.griddie.view.board.ortello;

import group3.griddie.controller.board.CellController;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.view.RootView;
import group3.griddie.view.board.CellView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Observable;
import java.util.Observer;

public class OrtelloBoardView extends RootView<Board> implements Observer {

    public OrtelloBoardView(Board board) {
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

                CellView cellView = new CellView(cell, new OrtelloActorView(null));
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
