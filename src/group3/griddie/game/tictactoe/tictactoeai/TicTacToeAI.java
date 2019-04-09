package group3.griddie.game.tictactoe.tictactoeai;

import group3.griddie.game.Game;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.tictactoe.tictactoeai.BoardSimulated;
import group3.griddie.game.tictactoe.tictactoeai.CellSimulated;
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
    private CellSimulated[][][] bestPosition = new CellSimulated[10][3][3];
    private int count;


    public TicTacToeAI(Game game, AIPlayer player, Actor.Type type) {
        this.game   = game;
        this.player = player;
        this.type = type;
    }
    public void setupBestPositions(){
    }
    public Cell predictMove() {
        return this.pickRandomCell();
    }

    private Cell pickRandomCell() {
        ArrayList<Cell> freeCells   = this.game.getBoard().getFreeSpots();
        Random random               = new Random();

        return freeCells.get(random.nextInt(freeCells.size()));
    }

    public Cell calculateMinMaxTree(){
        ArrayList<ArrayList<BoardSimulated>> boardinput = new ArrayList<>();
        long start_timer = System.nanoTime();
        Cell bestCell = null;
        ArrayList<ArrayList<BoardSimulated>> minMaxBoardHeap = returnMinMaxList(11, 0, boardinput, 0, start_timer);
        ArrayList<BoardSimulated> simulation = new ArrayList<>();
        ArrayList<BoardSimulated> zeros = new ArrayList<>();
        for (ArrayList<BoardSimulated> listBoards: minMaxBoardHeap){
            for (BoardSimulated boards : listBoards){
                if(boards.getStart() != 1) {
                    if (boards.getScore() > 0) {
                        int check = 0;
                        for (BoardSimulated pointerBack: boards.getPointerBack().getPointers()) {
                            if (pointerBack.getScore() < 0){
                                check = 1;
                            }

                            else if (pointerBack.getScore() == 0) {
                                zeros.add(boards);
                            }
                        }
                        if (check == 0){
                            simulation.add(boards);
                        }
                    }

                    else if (boards.getScore() == 0) {
                        zeros.add(boards);
                    }
                }
            }
        }

        simulation.addAll(zeros);
        System.out.println(simulation.get(0));
        bestCell = new Cell(simulation.get(0).getMove()[0], simulation.get(0).getMove()[1]);
        return bestCell;
    }

    public Cell calculateABpruningTree(){
        ArrayList<BoardSimulated> boardinput = new ArrayList<>();
        long start_timer = System.nanoTime();
        Cell bestCell = null;
        ArrayList<BoardSimulated> minMaxBoardHeap = returnABPruningTree(11, 0, boardinput, 0, start_timer, 0);
        ArrayList<BoardSimulated> simulation = new ArrayList<>();
        ArrayList<BoardSimulated> zeros = new ArrayList<>();
        for(BoardSimulated boards: minMaxBoardHeap){
            if(boards.getStart() != 1) {
                if (boards.getScore() > 0) {
                    int check = 0;
                    for (BoardSimulated pointerBack: boards.getPointerBack().getPointers()) {
                        if (pointerBack.getScore() < 0){
                            check = 1;
                        }

                        else if (pointerBack.getScore() == 0) {
                            zeros.add(boards);
                        }
                    }
                    if (check == 0){
                        simulation.add(boards);
                    }
                }

                else if (boards.getScore() == 0) {
                    zeros.add(boards);
                }
            }
        }

        simulation.addAll(zeros);
        System.out.println(simulation.get(0));
        bestCell = new Cell(simulation.get(0).getMove()[0], simulation.get(0).getMove()[1]);
        return bestCell;
    }



    public ArrayList<BoardSimulated>  returnABPruningTree(int depth, int start, ArrayList<BoardSimulated> boardinput, int count, long inputtime, int pointer){
        ArrayList<BoardSimulated> minMaxBoardHeap = boardinput;
        int depthMethod = depth;
        int startMethod = start;
        int countMethod = count;
        int pointerMethod = pointer;
        ArrayList<Cell> occupiedCells = null;
        long last_time = inputtime;
        if (startMethod == 0) {
            count = 0;
            minMaxBoardHeap = new ArrayList<>();
            occupiedCells = getOccupied(3, 3);
        }

        if(depthMethod > 0){
            if(startMethod == 0) {
                //init of heap
                BoardSimulated start_board = new BoardSimulated(3, 3);
                for (int c = 0; c < occupiedCells.size(); c++)
                    if (occupiedCells.get(c).isDisabled()) {
                        if (occupiedCells.get(c).getOccupant().getType() == this.type) {
                            start_board.setCells(occupiedCells.get(c).getX(), occupiedCells.get(c).getY(), "X");
                        } else if (occupiedCells.get(c).getOccupant().getType() != this.type) {
                            start_board.setCells(occupiedCells.get(c).getX(), occupiedCells.get(c).getY(), "0");
                        }
                    }

                System.out.println("START BOARD");
                System.out.println(start_board);
                start_board.setStart(1);
                pointerMethod++;
                minMaxBoardHeap.add(start_board);
            }

            else if(startMethod == 1){
                ArrayList<CellSimulated> selected_freespots = minMaxBoardHeap.get(0).getFreeSpots();
                for(int i = 0; i < minMaxBoardHeap.get(0).getFreeSpots().size(); i++){
                    BoardSimulated board = new BoardSimulated(3,3);
                    board.setCells(minMaxBoardHeap.get(0).getNewCellsArray());
                    board.setCells(selected_freespots.get(i).getX(), selected_freespots.get(i).getY(), "X");
                    board.setXor0("X");
                    board.setMinOrMax("MAX");
                    board.setPointerBack(minMaxBoardHeap.get(0));
                    minMaxBoardHeap.get(0).setPointer(board);
                    minMaxBoardHeap.add(board);
                    countMethod++;
                }
            }
            //starting with max and going in depth
            else if(startMethod > 1 ){
                int feather = minMaxBoardHeap.size();
                for (int b = pointerMethod; b < feather; b++) {
                    ArrayList<CellSimulated> selected_freespots = minMaxBoardHeap.get(b).getFreeSpots();
                    BoardSimulated selected_board = minMaxBoardHeap.get(b);
                    for(int i = 0; i < minMaxBoardHeap.get(b).getFreeSpots().size(); i++){
                        if (selected_board.getEndPoint() == 0) {
                            BoardSimulated board = new BoardSimulated(3,3);
                            board.setCells(selected_board.getNewCellsArray());
                            if ((startMethod % 2) == 0) {
                                board.setCells(selected_freespots.get(i).getX(), selected_freespots.get(i).getY(), "0");
                                board.setXor0("0");
                                board.setMinOrMax("MIN");

                            }
                            else if ((startMethod % 2) == 1) {
                                board.setCells(selected_freespots.get(i).getX(), selected_freespots.get(i).getY(), "X");
                                board.setXor0("X");
                                board.setMinOrMax("MAX");
                            }
                            board.setPointerBack(selected_board);
                            selected_board.setPointer(board);
                            minMaxBoardHeap.add(board);
                            countMethod++;
                            pointerMethod++;
                        }
                    }
                }
            }
        }

        if(depthMethod == 0){
            long time = System.nanoTime();
            int delta_time = (int) ((time - last_time) / 1000000);
            System.out.println("Time: " + delta_time + "ms");
            System.out.println("Start: " + startMethod);
            System.out.println("Depth: " + depthMethod);
            System.out.println("Count: " + countMethod);
            System.out.println("Pointer: " + pointerMethod);
            this.count = countMethod;
            return minMaxBoardHeap;
        }

        return returnABPruningTree(depthMethod -= 1, startMethod += 1, minMaxBoardHeap, countMethod, last_time, pointerMethod);
    }

    public ArrayList<ArrayList<BoardSimulated>>  returnMinMaxList(int depth, int start, ArrayList<ArrayList<BoardSimulated>> boardinput, int count, long inputtime){
        ArrayList<ArrayList<BoardSimulated>> minMaxBoardHeap = boardinput;
        int depthMethod = depth;
        int startMethod = start;
        int countMethod = count;
        ArrayList<Cell> occupiedCells = null;
        long last_time = inputtime;
        if (startMethod == 0) {
            count = 0;
            minMaxBoardHeap = new ArrayList<>();
            occupiedCells = getOccupied(3, 3);
        }

        if(depthMethod > 0){
            if(startMethod == 0) {
                //init of heap
                ArrayList<BoardSimulated> array_start = new ArrayList<>();
                BoardSimulated start_board = new BoardSimulated(3, 3);
                for (int c = 0; c < occupiedCells.size(); c++)
                    if (occupiedCells.get(c).isDisabled()) {
                        if (occupiedCells.get(c).getOccupant().getType() == this.type) {
                            start_board.setCells(occupiedCells.get(c).getX(), occupiedCells.get(c).getY(), "X");
                        } else if (occupiedCells.get(c).getOccupant().getType() != this.type) {
                            start_board.setCells(occupiedCells.get(c).getX(), occupiedCells.get(c).getY(), "0");
                        }
                    }

                System.out.println("START BOARD");
                System.out.println(start_board);
                start_board.setStart(1);
                array_start.add(start_board);
                minMaxBoardHeap.add(array_start);
            }
            //starting with max and going in depth
            if(startMethod > 0 ){
                ArrayList<BoardSimulated> simulatedBoards = new ArrayList<>();
                for (int i = 0; i < minMaxBoardHeap.get(start - 1).size(); i++){
                    ArrayList<CellSimulated> selected_freespots = minMaxBoardHeap.get(start - 1).get(i).getFreeSpots();
                    BoardSimulated selected_board = minMaxBoardHeap.get(start - 1).get(i);
                    for (int b = 0; b < selected_freespots.size(); b++) {
                        if (selected_board.getEndPoint() == 0) {
                            BoardSimulated board = new BoardSimulated(3,3);
                            board.setCells(selected_board.getNewCellsArray());
                            if ((startMethod % 2) == 0) {
                                board.setCells(selected_freespots.get(b).getX(), selected_freespots.get(b).getY(), "0");
                                board.setXor0("0");
                                board.setMinOrMax("MIN");

                            }
                            else if ((startMethod % 2) == 1) {
                                board.setCells(selected_freespots.get(b).getX(), selected_freespots.get(b).getY(), "X");
                                board.setXor0("X");
                                board.setMinOrMax("MAX");
                            }

                            simulatedBoards.add(board);
                            board.setPointerBack(selected_board);
                            selected_board.setPointer(board);
                            countMethod++;
                        }
                    }
                }
                minMaxBoardHeap.add(simulatedBoards);
            }
        }

        if(depthMethod == 0){
            long time = System.nanoTime();
            int delta_time = (int) ((time - last_time) / 1000000);
            System.out.println("Time: " + delta_time + "ms");
            System.out.println("Start: " + startMethod);
            System.out.println("Depth: " + depthMethod);
            System.out.println("Count: " + countMethod);
            this.count = countMethod;
            return minMaxBoardHeap;
        }

        return returnMinMaxList(depthMethod -= 1, startMethod += 1, minMaxBoardHeap, countMethod, last_time);
    }

    private ArrayList<Cell> getOccupied(int height, int width) {
        ArrayList<Cell> occupied;
        occupied = this.game.getBoard().getCellsArray();
        return occupied;
    }



    private Cell pruning(){
        //TODO
        return new Cell(1,1);
    }
}