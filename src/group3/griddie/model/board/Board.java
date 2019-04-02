package group3.griddie.model.board;

import group3.griddie.model.Model;

public class Board extends Model {

    public enum Pattern {
        CHECKER,
        NONE,
    }

    private int width;
    private int height;
    private Pattern pattern;
    private Cell[][] cells;

    public Board(int width, int height, Pattern pattern) {
        this.width = width;
        this.height = height;
        this.pattern = pattern;

        cells = new Cell[width][height];
    }

    public Cell getCell(int column, int row) {
        return cells[column][row];
    }

    public void setCell(Cell cell, int column, int row) {
        cells[column][row] = cell;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pattern getPattern() {
        return pattern;
    }

}
