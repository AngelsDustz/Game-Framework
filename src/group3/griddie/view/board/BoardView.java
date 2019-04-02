package group3.griddie.view.board;

import group3.griddie.controller.board.BoardController;
import group3.griddie.model.board.Board;
import group3.griddie.view.View;
import group3.griddie.view.board.component.CellView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class BoardView extends View<BoardController> implements Observer {
    public BoardView(BoardController controller, Board board) {
        super(board, new GridPane());
        this.setController(controller);
        board.addObserver(this);
    }

    @Override
    public void initializeView() {
        System.out.println("Initializing board view");
        Board board = (Board) getModel();
        GridPane root = (GridPane) getParent();

        for (int c = 0; c < board.getHeight(); c++) {
            for (int r = 0; r < board.getWidth(); r++) {
                switch (board.getPattern()) {
                    case NONE:
                        root.add(new CellView(Color.WHITE), c, r);
                        break;
                    case CHECKER:
                        root.add(new CellView((c + r) % 2 == 0 ? Color.WHITE : Color.BLACK), c, r);
                        break;
                }
            }
        }
    }

    @Override
    public void initializeControls() {

    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("Hello from BoarcView, we received an update!");
    }
}
