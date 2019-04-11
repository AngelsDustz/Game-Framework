package group3.griddie.view.game.board;

import group3.griddie.model.board.Board;
import group3.griddie.view.View;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class BoardView extends View {

    private Board board;

    public BoardView(Board board) {
        super(new GridPane(), null);

        this.board = board;

        GridPane root = (GridPane) getRoot();

        for (int c = 0; c < board.getWidth(); c++) {
            for (int r = 0; r < board.getHeight(); r++) {
                CellView cellView = new CellView(board.getCell(c, r));
                cellView.setImage(new Image("assets/images/node.png"));

                root.add(cellView, c, r);
            }
        }
    }
}
