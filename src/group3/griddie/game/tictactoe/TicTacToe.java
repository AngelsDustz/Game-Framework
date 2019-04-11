package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.network.NetworkHelperThread;
import group3.griddie.viewOLD.ViewOLD;
import group3.griddie.viewOLD.board.tictactoe.TicTacToeBoardViewOLD;

public class TicTacToe extends Game {
    private static String IP = "134.209.93.232";
    private static int PORT = 7789;
    public TicTacToe(String game) {
        super(game);

        NetworkHelperThread thread = new NetworkHelperThread(IP,PORT);
        Thread thread_network = new Thread(thread);
        thread_network.start();
        //addPlayer(new RemotePlayer(this,Actor.Type.TYPE_1, "Remote Player", thread.getNetworkRunner()));
    }

    @Override
    public boolean onPlayerMove(Player player, int column, int row) {
        Cell cell = getBoard().getCell(column, row);

        if (cell.isDisabled()) {
            return false;
        }

        System.out.println(player.getActorType());

        TicTacToeActor actor = new TicTacToeActor(player.getActorType());

        player.registerActor(actor);

        cell.setOccupant(actor);
        cell.setDisabled(true);

        return true;
    }

    @Override
    protected Board createBoard() {
        return new Board(3, 3);
    }

    @Override
    protected ViewOLD<Board> createBoardView(Board board) {
        return new TicTacToeBoardViewOLD(board);
    }

    @Override
    protected void onInit() {
//        AIPlayer aiPlayer = new AIPlayer(this, Actor.Type.TYPE_2, "AI Player");
//        aiPlayer.setDifficulty(AIPlayer.Difficulty.DIFFICULTY_HARD);
//        aiPlayer.setGameAI(new TicTacToeAI(this, aiPlayer));
//
//        lobby.join(aiPlayer);
//        lobby.join(new HumanPlayer(this,Actor.Type.TYPE_1, "Human Player"));
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onTick() {
        Board board = this.getBoard();

        if (this.checkIfWon(board) != null) {
            this.stop();
        }
    }

    public Actor.Type checkIfWon(Board board) {
        Actor.Type check = checkIfColumnWon(board);
        if (check != null) return check;

        check = checkIfRowWon(board);
        if (check != null) return check;

        check = checkIfDiagonalWon(board);
        if (check != null) return check;

        return null;
    }

    public Actor.Type checkIfColumnWon(Board board) {
        for (int i=0;i<board.getWidth();i++) {
            Cell toCheck            = board.getCell(i, 0);
            TicTacToeActor toActor  = (TicTacToeActor) toCheck.getOccupant();
            boolean check           = true;
            if (toActor == null) {
                continue;
            }

            for (int c=1;c<board.getHeight();c++) {
                Cell nextCheck              = board.getCell(i, c);
                TicTacToeActor nextActor    = (TicTacToeActor) nextCheck.getOccupant();
                if (nextActor == null) {
                    check = false;
                    continue;
                }

                if (toActor.getType() != nextActor.getType()) {
                    check = false;
                }
            }

            if (check) {
                return toActor.getType();
            }
        }

        return null;
    }

    private Actor.Type checkIfRowWon(Board board) {
        for (int i=0;i<board.getWidth();i++) {
            Cell toCheck            = board.getCell(0, i);
            TicTacToeActor toActor  = (TicTacToeActor) toCheck.getOccupant();
            boolean check           = true;
            if (toActor == null) {
                continue;
            }

            for (int c=1;c<board.getHeight();c++) {
                Cell nextCheck              = board.getCell(c, i);
                TicTacToeActor nextActor    = (TicTacToeActor) nextCheck.getOccupant();
                if (nextActor == null) {
                    check = false;
                    continue;
                }

                if (toActor.getType() != nextActor.getType()) {
                    check = false;
                }
            }

            if (check) {
                return toActor.getType();
            }
        }

        return null;
    }

    private Actor.Type checkIfDiagonalWon(Board board) {
        Cell cell       = board.getCell(1, 1);
        Cell topLeft    = board.getCell(0, 0);
        Cell topRight   = board.getCell(2, 0);
        Cell botLeft    = board.getCell(0, 2);
        Cell botRight   = board.getCell(2, 2);

        TicTacToeActor actor            = (TicTacToeActor) cell.getOccupant();
        TicTacToeActor topLeftActor     = (TicTacToeActor) topLeft.getOccupant();
        TicTacToeActor botRightActor    = (TicTacToeActor) botRight.getOccupant();

        if (actor == null) {
            return null;
        }

        if (topLeftActor != null) {
            if (actor.getType() == topLeftActor.getType()) {
                if (botRightActor != null) {
                    if (actor.getType() == botRightActor.getType()) {
                        return actor.getType();
                    }
                }
            }
        }

        TicTacToeActor topRightActor    = (TicTacToeActor) topRight.getOccupant();
        TicTacToeActor botLeftActor     = (TicTacToeActor) botLeft.getOccupant();

        if (topRightActor != null) {
            if (actor.getType() == topRightActor.getType()) {
                if (botLeftActor != null) {
                    if (actor.getType() == botLeftActor.getType()) {
                        return actor.getType();
                    }
                }
            }
        }

        return null;
    }
}
