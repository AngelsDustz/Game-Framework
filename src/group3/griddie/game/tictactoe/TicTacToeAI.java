package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToeAI {
    private Game        game;
    private AIPlayer    player;
    private Actor.Type  type;

    public TicTacToeAI(Game game, AIPlayer player, Actor.Type type) {
        this.game   = game;
        this.player = player;
        this.type = type;
    }

    public Cell predictMove() {
        return this.pickRandomCell();
    }

    private Cell pickRandomCell() {
        ArrayList<Cell> freeCells   = this.game.getBoard().getFreeSpots();
        Random random               = new Random();

        return freeCells.get(random.nextInt(freeCells.size()));
    }

    public ArrayList<ArrayList<BoardSimulated>>  returnMinMaxList(int depth, int start, ArrayList<ArrayList<BoardSimulated>> boardinput, int count){
        ArrayList<ArrayList<BoardSimulated>> minMaxBoardHeap = boardinput;
        ArrayList<Cell> freeCells = null;
        ArrayList<Cell> occupiedCells = null;
        if (start == 0) {
            count = 0;
            minMaxBoardHeap = new ArrayList<>();
            freeCells = this.game.getBoard().getFreeSpots();
            occupiedCells = getOccupied(3, 3);
        }

        if(depth != 0){
            if(start == 0) {
                //init of heap
                ArrayList<BoardSimulated> array_start = new ArrayList<>();
                BoardSimulated start_board = new BoardSimulated(3, 3);
                for (int c = 0; c < occupiedCells.size(); c++) {
                    if (occupiedCells.get(c).getOccupant().getType() == Actor.Type.TYPE_1) {
                        start_board.setCells(occupiedCells.get(c).getX(), occupiedCells.get(c).getY(), "X");
                    } else if (occupiedCells.get(c).getOccupant().getType() == Actor.Type.TYPE_2) {
                        start_board.setCells(occupiedCells.get(c).getX(), occupiedCells.get(c).getY(), "0");
                    }
                }
                array_start.add(start_board);
                minMaxBoardHeap.add(array_start);
            }
            if (start == 1){
                ArrayList<BoardSimulated> array_start = new ArrayList<>();
                for (int i = 0; i < freeCells.size(); i++) {
                    BoardSimulated board = minMaxBoardHeap.get(start - 1).get(0);
                    if (this.type == Actor.Type.TYPE_1){
                        board.setCells(freeCells.get(i).getX(), freeCells.get(i).getY(), "X");
                        board.setXor0("X");
                        board.setMinOrMax("MAX");
                    }

                    else if(this.type == Actor.Type.TYPE_2){
                        board.setCells(freeCells.get(i).getX(), freeCells.get(i).getY(), "0");
                        board.setXor0("0");
                        board.setMinOrMax("MIN");
                    }
                    count++;
                    board.setMinOrMax("MAX");
                    array_start.add(board);
                    minMaxBoardHeap.get(start - 1).get(0).setPointer(board);
                }
                minMaxBoardHeap.add(array_start);
            }

            if(start != 1 && start != 0 ){
                ArrayList<BoardSimulated> simulatedBoards = new ArrayList<>();
                for (int i = 0; i < minMaxBoardHeap.get(start - 1).size(); i++){
                    ArrayList<CellSimulated> selected_freespots = minMaxBoardHeap.get(start - 1).get(i).getFreeSpots();
                    BoardSimulated selected_board = minMaxBoardHeap.get(start - 1).get(i);
                    for (int b = 0; b < selected_freespots.size(); b++){
                        BoardSimulated board = minMaxBoardHeap.get(start).get(i);
                        if(start % 2 == 1){
                            board.setCells(selected_freespots.get(b).getX(), selected_freespots.get(b).getY(), "0");
                            board.setXor0("0");
                            board.setMinOrMax("MIN");
                        }

                        else if(start % 2 == 0){
                            board.setCells(selected_freespots.get(b).getX(), selected_freespots.get(b).getY(), "X");
                            board.setXor0("X");
                            board.setMinOrMax("MAX");
                        }

                        simulatedBoards.add(board);
                        selected_board.setPointer(board);
                        count++;
                    }
                }
                minMaxBoardHeap.add(simulatedBoards);
            }
        }

        if(depth == 0){
            return minMaxBoardHeap;
        }
        return returnMinMaxList(depth--, start++, minMaxBoardHeap, count);
    }

    private ArrayList<Cell> getOccupied(int height, int width) {
        ArrayList<Cell> occupied = new ArrayList<>();
        Cell[][] cells = this.game.getBoard().getCells();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (cells[col][row].isDisabled()){
                    occupied.add(cells[col][row]);
                }
            }
        }
        return occupied;
    }



    private Cell pruning(){
        //TODO
        return new Cell(1,1);
    }
    public class BoardSimulated {
        public int width;
        public int height;
        public CellSimulated[][] cells;
        public ArrayList<BoardSimulated> pointers = new ArrayList<>();
        public int score;
        public String minOrMax;
        public String xor0;

        public BoardSimulated(int width, int height){
            this.width = width;
            this.height = height;

            cells = new CellSimulated[width][height];

            for (int c = 0; c < width; c++) {
                for (int r = 0; r < height; r++) {
                    cells[c][r] = new CellSimulated(c, r);
                }
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
            return cells[collumn][row];
        }

        public void setCells(int x, int y, String occupant) {
            getCells(x,y).setOccupant(occupant);
        }
    }

    public class CellSimulated{
        public String MinOrMax;
        public boolean disabled;

        public int x;
        public int y;

        public CellSimulated(int c, int r) {
            this.x = c;
            this.y = r;

            disabled = false;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setOccupant(String occupant) {
            this.MinOrMax = occupant;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }

}
