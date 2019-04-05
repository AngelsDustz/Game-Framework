package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.game.tictactoe.TicTacToeAI;
import group3.griddie.model.board.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {
    private Difficulty difficulty;
    private TicTacToeAI tttAI;

    /**
     * Difficulty ENUMs.
     */
    public static enum Difficulty {
        DIFFICULTY_EASY,
        DIFFICULTY_MEDIUM,
        DIFFICULTY_HARD
    }

    public AIPlayer(Game game, String name) {
        super(game, name);
        this.difficulty = Difficulty.DIFFICULTY_EASY;
    }


    public AIPlayer(Game game, Difficulty difficulty) {
        super(game, "AI");
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public TicTacToeAI getTicTacToeAI() {
        if (this.tttAI == null) {
            this.tttAI = new TicTacToeAI(this.getGame(), this);
        }

        return this.tttAI;
    }

    @Override
    protected void onInit() {
    }

    @Override
    protected void onTick() {doRandomMove(getOptions());
    }

    private void doRandomMove(List<List<Cell>> options){
        Cell random_cell = null;
        List<Cell> random = options.get(new Random().nextInt(options.size()));
        if(!random.isEmpty()) {
            random_cell = random.get(new Random().nextInt(random.size()));
            getGame().playerMove(this, random_cell.getX(), random_cell.getY());
        }
    }

    private List<List<Cell>> getOptions(){
        Cell[][] cells = getGame().getBoard().getCells();
        List<List<Cell>> options = new ArrayList<List<Cell>>();
        ArrayList<Cell> row_cells = new ArrayList<Cell>();
        int b = 0;
        for(int i = 0; i < cells.length; i++) {
            Cell selected_cell = cells[b][i];
            if (i == 0) {
                row_cells = new ArrayList<Cell>();
            }

            if (!selected_cell.isDisabled()) {
                row_cells.add(selected_cell);
            }

            if (b <= 2) {
                options.add(row_cells);
            }

            if (i == 2 && b < 2) {
                i = 0;
                b++;
            }
        }
        return options;
    }

    private ArrayList<ArrayList<List<Integer>>> returnMinMax(ArrayList<List<Cell>> options, int depth){
        ArrayList<ArrayList<List<Integer>>> minMaxHeap = new ArrayList<>();
        ArrayList<ArrayList<List<Cell>>> minMaxHeapBoardState = new ArrayList<>();
        Cell[][] cells = getGame().getBoard().getCells();
        for (int i = 0; i < getPotential(options); i++){

        }
        return minMaxHeap;
    }

    private int getPotential(ArrayList<List<Cell>> options){
        int potential = 0;
        for(int i = 0; i < options.size(); i++){
            potential += options.get(i).size();
        }
        return potential;
    }

    private Cell pruning(){
        //TODO
        return new Cell(1,1);
    }

    @Override
    protected void onStartTurn() {
        Cell predicted = this.getTicTacToeAI().predictMove();
        this.getGame().playerMove(this, predicted.getX(), predicted.getY());
    }

    @Override
    protected void onEndTurn() {
    }
}
