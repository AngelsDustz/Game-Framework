package group3.griddie.game.tictactoe.tictactoeai;

import group3.griddie.model.board.Board;

import java.util.ArrayList;

public class BoardSimulated {
    public int width;
    public int height;
    public CellSimulated[][] cells;
    public ArrayList<BoardSimulated> pointers = new ArrayList<>();
    public int score;
    public String minOrMax;
    public String xor0;
    public int endPoint = 0;

    public BoardSimulated(int width, int height){
        this.width = width;
        this.height = height;
        this.endPoint = 0;
        this.cells = new CellSimulated[width][height];

        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                cells[c][r] = new CellSimulated(c, r);
            }
        }
    }

    public int getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public void ifWon(){
        //check columns
        for(int i = 0; i < this.width; i++){
            if(!this.cells[i][this.height - 1].getMinOrMax().equals(" ") && this.cells[i][this.height - 1].getMinOrMax().equals(this.cells[i][this.height - 2].getMinOrMax())
                    && this.cells[i][this.width - 2].getMinOrMax().equals(this.cells[i][this.width - 3].getMinOrMax())){
                System.out.println("columns");
                this.endPoint = 1;
            }
        }
        //check rows
        for(int i = 0; i < this.height; i++){
            if(!this.cells[this.width - 3][i].getMinOrMax().equals(" ") && this.cells[this.width - 1][i].getMinOrMax().equals(this.cells[this.width - 2][i].getMinOrMax())
                    && this.cells[this.width - 2][i].getMinOrMax().equals(this.cells[this.width - 3][i].getMinOrMax())){
                System.out.println("rows");
                this.endPoint = 1;
            }
        }
        //check diagonal
        if (!this.cells[this.width - this.width][this.width - this.width].getMinOrMax().equals(" ") &&
                this.cells[this.width - this.width][this.width - this.width].getMinOrMax().equals(this.cells[this.width + 1 - this.width][1].getMinOrMax())
                    && this.cells[this.width - 1][this.height -1].getMinOrMax().equals(this.cells[this.width + 1 - this.width][1].getMinOrMax())) {
            System.out.println("diagonal");
            this.endPoint = 1;
        }


        //check anti-diagonal
        if (!this.cells[this.width - 1][this.width - this.width].getMinOrMax().equals(" ") &&
                this.cells[this.width - 1][this.width - this.width].getMinOrMax().equals(this.cells[this.width + 1 - this.width][1].getMinOrMax())
                    && this.cells[this.width - this.width][this.height - 1].getMinOrMax().equals( this.cells[this.width + 1 - this.width][1].getMinOrMax())) {
                System.out.println("anti-diagonal");
                this.endPoint = 1;
        }
    }

    public void giveScore(){
        //TODO
    }

    public void setMinOrMax(String minOrMax){
        this.minOrMax = minOrMax;
    }

    public void setPointer(BoardSimulated pointer) {
        this.pointers.add(pointer);
    }

    public void setXor0(String xor0){
        this.xor0 = xor0;
    }

    public void setminOrMax(String minOrMax){
        this.minOrMax = minOrMax;
    }

    public ArrayList<CellSimulated> getFreeSpots() {
        ArrayList<CellSimulated> freeCells = new ArrayList<>();

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (!this.cells[col][row].isDisabled()) {
                    freeCells.add(this.cells[col][row]);
                }
            }
        }
        return freeCells;
    }

    public CellSimulated getCells(int collumn, int row) {
        return this.cells[collumn][row];
    }

    public CellSimulated[][] getNewCellsArray() {
        CellSimulated[][] returnCell = this.cells.clone();
        for (int i = 0; i < this.width; i++){
            for(int b = 0; b < this.height; b++){
                cells[i][b] = this.cells[i][b];
            }
        }
        return returnCell;
    }

    public void setCells(int x, int y, String occupant) {
        getCells(x,y).setOccupant(occupant);
        ifWon();
    }

    public void setCells(CellSimulated[][] Gcells) {
        this.cells = Gcells;
    }

    @Override
    public String toString() {
        ArrayList<CellSimulated> cellSimulateds = new ArrayList<>();
        String returnString = "===============\n\r";
        for(int i = 0; i < this.width; i++){
            for (int b = 0; b < this.width; b++){
                cellSimulateds.add(this.cells[i][b]);
            }
        }

        for (int i = 0; i < cellSimulateds.size(); i++){
            String block = String.format("[ %s ]", cellSimulateds.get(i).getMinOrMax());
            if(i % this.width == 0 && i != 0){
                returnString = returnString + "\n\r" + block;
            }

            else if(i % this.width != 0 || i == 0){
                returnString = returnString + block;
            }
        }
        returnString = returnString + "\n\r" + "===============";
        return returnString;
    }
}
