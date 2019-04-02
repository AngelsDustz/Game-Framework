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
    private Grid grid;

    public Board(int width, int height, Pattern pattern) {
        this.width = width;
        this.height = height;
        this.pattern = pattern;

        grid = new Grid(width, height);
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
