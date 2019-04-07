package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;

public class HumanPlayer extends Player {

    public HumanPlayer(Game game, Actor.Type type, String name) {
        super(game, type, name);
    }

    @Override
    protected void onInit() {
        Cell[][] cells = getGame().getBoard().getCells();

        for (int c = 0; c < cells.length; c++ ){
            for (int r = 0; r < cells[c].length; r++) {
                final int column = c;
                final int row = r;

                Cell cell = cells[c][r];
                cell.addInteractListener(() -> {
                    getGame().playerMove(this, column, row);
                });
            }
        }
    }

    @Override
    protected void onTick() {

    }

    @Override
    protected void onStartTurn() {

    }

    @Override
    protected void onEndTurn() {

    }
}
