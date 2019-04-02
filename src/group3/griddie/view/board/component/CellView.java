package group3.griddie.view.board.component;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CellView extends Canvas {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    public CellView(Color color) {
        super(WIDTH, HEIGHT);

        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
    }

}
