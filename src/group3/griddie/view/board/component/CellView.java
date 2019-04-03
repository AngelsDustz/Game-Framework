package group3.griddie.view.board.component;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CellView extends Canvas {
    private static final int WIDTH  = 75;
    private static final int HEIGHT = 75;

    public CellView(Color color) {
        super(WIDTH, HEIGHT);

        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, WIDTH, HEIGHT);
    }

}
