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
                    BoardSimulated board = new BoardSimulated(3, 3);
                    for (int b = 0; b < occupiedCells.size(); b++) {
                        if (occupiedCells.get(b).getOccupant().getType() == Actor.Type.TYPE_1) {
                            board.setCells(occupiedCells.get(b).getX(), occupiedCells.get(b).getY(), "X");
                        } else if (occupiedCells.get(b).getOccupant().getType() == Actor.Type.TYPE_2) {
                            board.setCells(occupiedCells.get(b).getX(), occupiedCells.get(b).getY(), "0");
                        }
                    }

                    if (this.type == Actor.Type.TYPE_1){
                        board.setCells(freeCells.get(i).getX(), freeCells.get(i).getY(), "X");
                    }

                    else if(this.type == Actor.Type.TYPE_2){
                        board.setCells(freeCells.get(i).getX(), freeCells.get(i).getY(), "0");
                    }
                    count++;
                    array_start.add(board);
                }
                minMaxBoardHeap.add(array_start);
            }

            if(start != 1 && start != 0 ){
                ArrayList<BoardSimulated> simulatedBoards = new ArrayList<>();
                int start_for = 0;
                for (int i = 0; i < minMaxBoardHeap.get(start).size(); i++){
                    ArrayList<CellSimulated> selected_freespots = minMaxBoardHeap.get(start).get(i).getFreeSpots();
                    for (int b = 0; b < selected_freespots.size(); b++){
                        BoardSimulated board = minMaxBoardHeap.get(start).get(i);
                        if(start % 2 == 1){
                            board.setCells(selected_freespots.get(b).getX(), selected_freespots.get(b).getY(), "0");
                        }

                        else if(start % 2 == 0){
                            board.setCells(selected_freespots.get(b).getX(), selected_freespots.get(b).getY(), "X");
                        }

                        simulatedBoards.add(board);

                        if (b > 0 && b < selected_freespots.size() - 1){
                            board.setPointer(simulatedBoards.get(b + start_for));
                        }

                        else if(b > 0 && b < selected_freespots.size()){
                            board.setPointer(simulatedBoards.get(start_for));
                        }

                        if (b == selected_freespots.size() - 1){
                            start_for = b;
                        }
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
        public BoardSimulated pointer;

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

        public void setPointer(BoardSimulated pointer) {
            this.pointer = pointer;
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
