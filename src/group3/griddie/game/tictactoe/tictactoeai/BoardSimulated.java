package group3.griddie.game.tictactoe.tictactoeai;

import group3.griddie.model.board.Board;

import java.util.ArrayList;

public class BoardSimulated {
    public int width;
    public int height;
    public CellSimulated[][] cells;
    public ArrayList<BoardSimulated> pointers = new ArrayList<>();
    public int score;
    public int[] move;
    public String minOrMax;
    public String xor0;
    public int endPoint = 0;

    public BoardSimulated(int width, int height){
        this.width = width;
        this.height = height;
        this.endPoint = 0;
        this.move = new int[2];
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

    public ArrayList<BoardSimulated> getPointers() {
        return pointers;
    }

    public int getScore() {
        return score;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public void ifWon(){
        //check rows
        for(int i = 0; i < this.width; i++){
            if(!this.cells[i][this.height - 1].getMinOrMax().equals(" ") && this.cells[i][this.height - 1].getMinOrMax().equals(this.cells[i][this.height - 2].getMinOrMax())
                    && this.cells[i][this.width - 2].getMinOrMax().equals(this.cells[i][this.width - 3].getMinOrMax())){
                this.endPoint = 1;
                giveScore(this.cells[i][this.height - 1].getMinOrMax());
            }
        }
        //check columns
        for(int i = 0; i < this.height; i++){
            if(!this.cells[this.width - 3][i].getMinOrMax().equals(" ") && this.cells[this.width - 1][i].getMinOrMax().equals(this.cells[this.width - 2][i].getMinOrMax())
                    && this.cells[this.width - 2][i].getMinOrMax().equals(this.cells[this.width - 3][i].getMinOrMax())){
                this.endPoint = 1;
                giveScore(this.cells[this.width - 3][i].getMinOrMax());
            }
        }
        //check diagonal
        if (!this.cells[this.width - this.width][this.width - this.width].getMinOrMax().equals(" ") &&
                this.cells[this.width - this.width][this.width - this.width].getMinOrMax().equals(this.cells[this.width + 1 - this.width][1].getMinOrMax())
                    && this.cells[this.width - 1][this.height -1].getMinOrMax().equals(this.cells[this.width + 1 - this.width][1].getMinOrMax())) {
            this.endPoint = 1;
            giveScore(this.cells[this.width - this.width][this.width - this.width].getMinOrMax());
        }


        //check anti-diagonal
        if (!this.cells[this.width - 1][this.width - this.width].getMinOrMax().equals(" ") &&
                this.cells[this.width - 1][this.width - this.width].getMinOrMax().equals(this.cells[this.width + 1 - this.width][1].getMinOrMax())
                    && this.cells[this.width - this.width][this.height - 1].getMinOrMax().equals( this.cells[this.width + 1 - this.width][1].getMinOrMax())) {
                this.endPoint = 1;
                giveScore(this.cells[this.width - 1][this.width - this.width].getMinOrMax());
        }
    }

    public void giveScore(String value){
        if(value.equals(this.minOrMax)){
            this.score = 1;
        }

        else if(!value.equals(this.minOrMax)){
            this.score = -1;
        }
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
                if(this.cells[col][row].getMinOrMax().equals(" ")){
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
        CellSimulated[][] returnCell = new CellSimulated[this.width][this.height];
        for (int i = 0; i < this.width; i++){
            for(int b = 0; b < this.height; b++){
                CellSimulated temp = new CellSimulated(this.cells[i][b].getX(), this.cells[i][b].getY());
                String temp_string = new String(this.cells[i][b].getMinOrMax());
                temp.setOccupant(temp_string);
                returnCell[i][b] = temp;
            }
        }
        return returnCell;
    }

    public void setCells(int x, int y, String occupant) {
        this.minOrMax = occupant;
        getCells(x,y).setOccupant(occupant);
        this.move[0] = x;
        this.move[1] = y;
        ifWon();
    }

    public int[] getMove() {
        return this.move;
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
