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

    public void calculateMinMaxTree(){
        ArrayList<ArrayList<BoardSimulated>> boardinput = new ArrayList<>();
        ArrayList<ArrayList<BoardSimulated>> minMaxBoardHeap = returnMinMaxList(10, 0, boardinput, 0);
    }

    public ArrayList<ArrayList<BoardSimulated>>  returnMinMaxList(int depth, int start, ArrayList<ArrayList<BoardSimulated>> boardinput, int count){
        ArrayList<ArrayList<BoardSimulated>> minMaxBoardHeap = boardinput;
        int depthMethod = depth;
        int startMethod = start;
        int countMethod = count;
        ArrayList<Cell> freeCells = null;
        ArrayList<Cell> occupiedCells = null;
        if (startMethod == 0) {
            count = 0;
            minMaxBoardHeap = new ArrayList<>();
            freeCells = this.game.getBoard().getFreeSpots();
            occupiedCells = getOccupied(3, 3);
        }

        if(depthMethod > 0){
            if(startMethod == 0) {
                //init of heap
                System.out.println("INIT HEAP");
                ArrayList<BoardSimulated> array_start = new ArrayList<>();
                BoardSimulated start_board = new BoardSimulated(3, 3);
                /**
                for (int c = 0; c < occupiedCells.size(); c++) {
                    if (occupiedCells.get(c).getOccupant().getType() == this.type) {
                        System.out.println("filled: X");
                        start_board.setCells(occupiedCells.get(c).getX(), occupiedCells.get(c).getY(), "X");
                    } else if (occupiedCells.get(c).getOccupant().getType() != this.type) {
                        start_board.setCells(occupiedCells.get(c).getX(), occupiedCells.get(c).getY(), "0");
                        System.out.println("filled: 0");
                    }
                }
                 */
                System.out.println(start_board);
                System.out.println(occupiedCells);
                array_start.add(start_board);
                minMaxBoardHeap.add(array_start);
            }
            //starting with max and going in depth
            if(startMethod > 0 ){
                ArrayList<BoardSimulated> simulatedBoards = new ArrayList<>();
                for (int i = 0; i < minMaxBoardHeap.get(start - 1).size(); i++){
                    ArrayList<CellSimulated> selected_freespots = minMaxBoardHeap.get(start - 1).get(i).getFreeSpots();
                    BoardSimulated selected_board = minMaxBoardHeap.get(start - 1).get(i);
                    System.out.println("ARRAY SIZE: " + minMaxBoardHeap.get(start - 1).size());
                    for (int b = 0; b < selected_freespots.size(); b++) {
                        if (selected_board.getEndPoint() == 0) {
                            BoardSimulated board = new BoardSimulated(3,3);
                            CellSimulated[][] array = selected_board.getNewCellsArray();
                            board.setCells(array);
                            if ((startMethod % 2) == 0) {
                                board.setCells(selected_freespots.get(b).getX(), selected_freespots.get(b).getY(), "0");
                                board.setXor0("0");
                                board.setMinOrMax("MIN");
                                System.out.println("MIN:" + startMethod % 2);

                            }
                            else if ((startMethod % 2) == 1) {
                                board.setCells(selected_freespots.get(b).getX(), selected_freespots.get(b).getY(), "X");
                                board.setXor0("X");
                                board.setMinOrMax("MAX");
                                System.out.println("MAX:" + startMethod % 2);
                            }
                            System.out.println(board);
                            System.out.println(selected_board);
                            simulatedBoards.add(board);
                            selected_board.setPointer(board);
                            countMethod++;
                        }
                    }
                }
                minMaxBoardHeap.add(simulatedBoards);
            }
        }

        if(depthMethod == 0){
            System.out.println("Start: " + startMethod);
            System.out.println("Depth: " + depthMethod);
            System.out.println("Count: " + countMethod);
            return minMaxBoardHeap;
        }

        return returnMinMaxList(depthMethod -= 1, startMethod += 1, minMaxBoardHeap, countMethod);
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