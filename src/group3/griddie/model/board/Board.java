package group3.griddie.model.board;

import group3.griddie.model.Model;
import group3.griddie.model.board.actor.Actor;
import java.util.ArrayList;

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
                cells[c][r] = addChild(new Cell(c, r));
            }
        }
    }

    /**
     * Constructor to make a hard copy.
     *
     * @param board The board to clone.
     */
    public Board(Board board) {
        this.width  = board.width;
        this.height = board.height;
        this.cells  = new Cell[width][height];

        for (int i=0;i<this.width;i++) {
            for (int c=0;c<this.height;c++) {
                this.cells[i][c] = board.cells[i][c];
            }
        }
    }

    /**
     * Cells getter.
     *
     * @return All cells in 2d array.
     */
    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int column, int row) {
        if (column < 0 || column >= this.width) {
            return null;
        }

        if (row < 0 || row >= this.height) {
            return null;
        }

        return cells[column][row];
    }

    /**
     * Function to find all ajacent cells.
     *
     * @param cell The cell to check around.
     * @return Array of all adjacent cells, including NULL.
     */
    public Cell[] getAdjacentCells(Cell cell) {
        Cell[] cells = new Cell[9];
        cells[0] = getCell(cell.getX()-1, cell.getY()-1);
        cells[1] = getCell(cell.getX(), cell.getY()-1);
        cells[2] = getCell(cell.getX()+1, cell.getY()-1);
        cells[3] = getCell(cell.getX()-1, cell.getY());
        cells[4] = cell; // We are in the middle.
        cells[5] = getCell(cell.getX()+1, cell.getY());
        cells[6] = getCell(cell.getX()-1, cell.getY()+1);
        cells[7] = getCell(cell.getX(), cell.getY()+1);
        cells[8] = getCell(cell.getX()+1, cell.getY()+1);

        return cells;
    }

    public ArrayList<Cell> getSpotsByActorType(Actor.Type type) {
        ArrayList<Cell> cells = new ArrayList<>();

        for (int i=0;i<this.width;i++) {
            for (int c=0;c<this.height;c++) {
                Cell cell = this.getCell(i, c);

                if (cell != null) {
                    if (cell.isOccupied() && cell.getOccupant().getType() == type) {
                        cells.add(cell);
                    }
                }
            }
        }

        return cells;
    }

    public ArrayList<Cell> getCellsArray() {
        ArrayList<Cell> cells = new ArrayList<>();

        for (int i=0;i<this.width;i++) {
            for (int c=0;c<this.height;c++) {
                cells.add(this.getCell(i, c));
            }
        }

        return cells;
    }

    public void setCell(int column, int row, Cell cell) {
        this.cells[column][row] = cell;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return width*height;
    }

    public ArrayList<Cell> getFreeSpots() {
        ArrayList<Cell> freeCells = new ArrayList<>();

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (!this.cells[col][row].isDisabled()) {
                    if (this.cells[col][row].getOccupant() == null) {
                        freeCells.add(this.cells[col][row]);
                    }
                }
            }
        }

        return freeCells;
    }

    public Cell findFirstActorTypeNotEqual(Actor.Type actorType) {
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                Actor actor = cells[col][row].getOccupant();
                if (actor == null) {
                    System.out.println("NO OCCUPANT!");
                    continue;
                }

                if (actor.getType() != actorType) {
                    return cells[col][row];
                }
            }
        }

        return null;
    }

    public String toString() {
        String data = "";

        for (int i=0;i<this.height;i++) {
            for (int c=0;c<this.width;c++) {
                Cell cell = this.getCell(c, i);

                if (cell.getOccupant() == null) {
                    data += "[ ]";
                    continue;
                }

                Actor.Type type = cell.getOccupant().getType();

                if (type == null) {
                    data += "[ ]";
                }

                if (type == Actor.Type.TYPE_1) {
                    data += "[X]";
                }

                if (type == Actor.Type.TYPE_2) {
                    data += "[O]";
                }
            }

            data += "\n";
        }

        return data;
    }
}
