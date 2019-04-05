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

    public Cell[][] getCells() {
        return cells;
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

    public int getSize() {
        return width*height;
    }

    public ArrayList<Cell> getFreeSpots() {
        ArrayList<Cell> freeCells = new ArrayList<>();

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (!this.cells[col][row].isDisabled()) {
                    freeCells.add(this.cells[col][row]);
                }
            }
        }

        return freeCells;
    }

    public Cell findFirstActorTypeNotEqual(Actor.Type actorType) {
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (!this.cells[col][row].isDisabled()) {
                    Actor actor = cells[col][row].getOccupant();
                    if (actor == null) {
                        continue;
                    }

                    if (actor.getType() != actorType) {
                        return cells[col][row];
                    }
                }
            }
        }

        return null;
    }
}
