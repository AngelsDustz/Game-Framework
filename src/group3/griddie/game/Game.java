package group3.griddie.game;

import group3.griddie.model.board.Board;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.view.game.GameView;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class Game extends Scene implements Observer {

    public interface PlayerMove {
        void onMove(Player player, int x, int y);
    }

    private ArrayList<PlayerMove> playerMoveListeners;

    private Board board;
    private boolean started;
    private Player playerOnTurn;
    private String game;
    private int round;
    protected Lobby lobby;

    private GameView gameView;

    public Game(String game) {
        super(new AnchorPane());

        playerMoveListeners = new ArrayList<>();

        board = createBoard();

        lobby = new Lobby(2, this);
        lobby.addObserver(this);

        gameView = new GameView(this);

        AnchorPane root = (AnchorPane) getRoot();
        root.getChildren().add(gameView);
    }

    public void addOnPlayerMoveListener(PlayerMove listener) {
        this.playerMoveListeners.add(listener);
    }

    @Override
    public String toString() {
        return game;
    }

    public final void init() {
        onInit();
    }

    public final void start() {
        System.out.println("Starting game");
        round = 0;
        started = true;

        onStart();

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
        round++;

        if (playerOnTurn != null) {
            //playerOnTurn.endTurn();
        }

//        playerOnTurn = getNextPlayer();
//        playerOnTurn.startTurn();

        tick();
    }

    public void playerMove(Player player, int column, int row) {
        if (onPlayerMove(player, column, row)) {
            for(PlayerMove listener : playerMoveListeners) {
                listener.onMove(player, column, row);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayerOnTurn() {
        return playerOnTurn;
    }

    private Player getNextPlayer() {
        return lobby.getPlayer(round % 2);
    }

    public void placeActor(Actor actor, int x, int y) {
        Cell cell = board.getCell(x, y);
        cell.setOccupant(actor);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Lobby) {
            Lobby lobby = (Lobby) o;

            if (!started && lobby.isFull() && lobby.allReady()) {
                start();
            }
        }
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    protected abstract boolean onPlayerMove(Player player, int column, int row);

    protected abstract Board createBoard();

    protected abstract void onInit();

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract void onTick();

}
