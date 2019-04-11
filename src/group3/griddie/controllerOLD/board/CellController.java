package group3.griddie.controllerOLD.board;

import group3.griddie.controllerOLD.Controller;
import group3.griddie.model.board.Cell;

public class CellController extends Controller<Cell> {

    public CellController(Cell model) {
        super(model);
    }

    public void onClick() {
        Cell cell = getModel();
        cell.interact();
    }

}
