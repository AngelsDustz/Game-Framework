package group3.griddie.game.ortello;

import group3.griddie.game.Game;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.OrtelloActor;
import group3.griddie.view.View;
import group3.griddie.view.board.ortello.OrtelloBoardView;

public class Ortello extends Game {

    public Ortello() {
        super();

        addPlayer(new HumanPlayer(this, Actor.Type.TYPE_2, "Ai player"));
    }

    @Override
    public boolean onPlayerMove(Player player, int column, int row) {
        Cell cell = getBoard().getCell(column, row);

        if (cell.isDisabled()) {
            return false;
        }

        int index = getPlayers().indexOf(player);

        OrtelloActor actor = new OrtelloActor(
                index % 2 == 0 ? Actor.Type.TYPE_1 : Actor.Type.TYPE_2
        );

        player.registerActor(actor);

        cell.setOccupant(actor);
        cell.setDisabled(true);

        return true;
    }

    @Override
    protected Board createBoard() {
        return new Board(8,8);
    }

    @Override
    protected View<Board> createBoardView(Board board) {
        return new OrtelloBoardView(board);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onStart() {
        getBoard().getCell(3,3).setOccupant(new OrtelloActor(OrtelloActor.Type.TYPE_1));
        getBoard().getCell(4,4).setOccupant(new OrtelloActor(OrtelloActor.Type.TYPE_1));
        getBoard().getCell(4,3).setOccupant(new OrtelloActor(OrtelloActor.Type.TYPE_2));
        getBoard().getCell(3,4).setOccupant(new OrtelloActor(OrtelloActor.Type.TYPE_2));
    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onTick() {

    }
}
