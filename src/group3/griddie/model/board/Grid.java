package group3.griddie.model.board;

public class Grid {
    private Cell[][] nodes;

    public Grid(int width, int height) {
        nodes = new Cell[width][height];
    }

}
