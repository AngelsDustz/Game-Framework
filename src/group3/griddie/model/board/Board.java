package group3.griddie.model.board;

import group3.griddie.model.Model;

public class Board extends Model {

    private int width;
    private int height;
    private Cell[][] cells;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        cells = new Cell[width][height];

        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                cells[c][r] = new Cell();
            }
        }
    }

    public Cell getCell(int column, int row) {
        return cells[column][row];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
