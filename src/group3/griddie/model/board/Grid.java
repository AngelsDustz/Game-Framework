package group3.griddie.model.board;
import group3.griddie.model.Model;

public class Grid extends Model {
    private Cell[][] nodes;

    public Grid(int width, int height) {
        nodes = new Cell[width][height];
    }

}
