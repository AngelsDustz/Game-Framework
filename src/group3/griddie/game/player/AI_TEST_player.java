package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.model.board.Cell;

import java.util.ArrayList;
import java.util.Random;

public class AI_TEST_player extends Player {

    public AI_TEST_player(Game game, String name) {
        super(game, name);
    }

    @Override
    protected void onStartTurn() {

    }

    @Override
    protected void onEndTurn() {

    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onTick() {
        //THIS IS A VERY SIMPLE EXAMPLE OF HOW AI CAN WORK
        //THIS EXAMPLE JUST PICKS A RANDOM CELL AND IS NOT SMART IN ANY WAY

        Cell[][] cells = getGame().getBoard().getCells();
        ArrayList<Cell> options = new ArrayList<>();

        for (int c = 0; c < cells.length; c++ ){
            for (int r = 0; r < cells[c].length; r++) {
                Cell cell = cells[c][r];
                if (!cell.isDisabled()) {
                    options.add(cell);
                }
            }
        }

        if (options.size() == 0) {
            return;
        }

        Cell random = options.get(new Random().nextInt(options.size()));
        getGame().playerMove(this, random.getX(), random.getY());
    }
}
