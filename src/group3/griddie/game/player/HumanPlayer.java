package group3.griddie.game.player;

import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    protected void onInit() {
        Board board = getGame().getBoard();

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Cell cell = board.getCell(x, y);
                int finalX = x;
                int finalY = y;
                cell.addInteractListener(() -> {
                    if (isOnTurn()) {
                        System.out.println("Interacted with cell " + finalX + " " + finalY);
                    }
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
