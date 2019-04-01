package group3.griddie.model.board;

import group3.griddie.model.Model;

public class Board extends Model {

    private int width;
    private int height;
    private Grid grid;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Grid(width, height);
    }

}
