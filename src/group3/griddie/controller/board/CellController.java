package group3.griddie.controller.board;

import group3.griddie.controller.Controller;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.view.View;
import group3.griddie.view.board.CellView;

public class CellController extends Controller<Cell> {

    public CellController(Cell model) {
        super(model);
    }

    @Override
    protected View createView() {
        return new CellView((Cell) getModel());
    }

    public void onClick() {
        Cell cell = getModel();

        cell.setOccupant(new TicTacToeActor(TicTacToeActor.Type.O));
    }

}
