package group3.griddie.game;

import group3.griddie.controller.board.BoardController;
import group3.griddie.controller.game.LobbyController;
import group3.griddie.model.board.Board;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.view.View;
import group3.griddie.view.game.LobbyView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.util.Observable;
import java.util.Observer;

public abstract class Game extends Scene implements Observer {

    private Board board;
    private boolean started;
    private Player playerOnTurn;
    private String game;
    private int round;

    protected Lobby lobby;

    public Game(String game) {
        super(new BorderPane());
        this.game = game;

        lobby = new Lobby(2, this);
        lobby.addObserver(this);
    }

    @Override
    public String toString() {
        return game;
    }

    public final void init() {
        BorderPane root = (BorderPane) getRoot();

        board = createBoard();
        View<Board> boardView = createBoardView(board);
        boardView.init();
        boardView.setController(new BoardController(board));

        LobbyView lobbyView = new LobbyView(lobby);
        lobbyView.init();
        lobbyView.setController(new LobbyController(lobby));

        root.setCenter(lobbyView.getNode());
        //root.setCenter(boardView.getNode());

        onInit();
    }

    public final void start() {
        System.out.println("Starting");

        round = 0;
        started = true;

        onStart();

        for (Player player : lobby.getPlayers()) {
            player.init();
        }

        nextTurn();
    }

    public final void stop() {
        started = false;

        onStop();
    }

    public final void tick() {
        for (Player player : lobby.getPlayers()) {
            player.tick();
        }

        onTick();
    }

    public void nextTurn() {
        System.out.println("Next turn");

        round++;

        if (playerOnTurn != null) {
            playerOnTurn.endTurn();
        }

        playerOnTurn = getNextPlayer();
        playerOnTurn.startTurn();

        tick();

        System.out.println("END TURN");
    }

    public void playerMove(Player player, int column, int row) {
        if (playerOnTurn != player) {
            return;
        }

        if (onPlayerMove(player, column, row)) {
            nextTurn();
        }
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayerOnTurn() {
        return playerOnTurn;
    }

    private Player getNextPlayer() {
        System.out.println(round % 2);
        return lobby.getPlayer(round % 2);
    }

    public void placeActor(Actor actor, int x, int y) {
        Cell cell = board.getCell(x, y);
        cell.setOccupant(actor);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Lobby) {
            System.out.println("Lobby observer");

            Lobby lobby = (Lobby) o;

            if (!started && lobby.isFull()) {
                start();
            }
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    protected abstract boolean onPlayerMove(Player player, int column, int row);

    protected abstract Board createBoard();

    protected abstract View<Board> createBoardView(Board board);

    protected abstract void onInit();

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract void onTick();

}
