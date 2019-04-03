package group3.griddie.model.board;
import group3.griddie.model.Model;

public class Grid extends Model {
    private Cell[][] nodes;

    public Grid(int width, int height) {
        nodes = new Cell[width][height];
    }

    public Cell getCell(int width, int height) {
        return nodes[width][height];
    }

    public Cell[] getCollidingCells(int width, int height) {
        /**
         * [1][2][3]
         * [4]   [5]
         * [6][7][8]
         */
        Cell[] colliding = new Cell[8];

        int passed = 0;
        for (int hMod=-1;hMod>1;hMod++) {
            for (int wMod=-1;wMod>1;wMod++) {
                if (nodes[width+wMod][height+hMod] != null) {
                    colliding[passed] = nodes[width+wMod][height+hMod];
                }
            }

            passed++;
        }

        return colliding;
    }
}
