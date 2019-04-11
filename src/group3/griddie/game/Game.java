package group3.griddie.game;

import group3.griddie.model.board.Board;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.view.game.GameView;
import group3.griddie.viewOLD.ViewOLD;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.util.Observable;
import java.util.Observer;

public abstract class Game extends Scene implements Observer {

    private Board board;
    private boolean started;
    private Player playerOnTurn;
    private String game;
    private int round;
    protected Lobby lobby;

    private GameView gameView;

    public Game(String game) {
        super(new AnchorPane());

        board = createBoard();

        lobby = new Lobby(2, this);
        lobby.addObserver(this);

        gameView = new GameView(this);

        AnchorPane root = (AnchorPane) getRoot();
        root.getChildren().add(gameView);
    }

    public String getGame() {
        return game;
    }

    @Override
    public String toString() {
        return game;
    }

    public final void init() {
        onInit();
    }

    public final void start() {
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
        round++;

        if (playerOnTurn != null) {
            playerOnTurn.endTurn();
        }

        playerOnTurn = getNextPlayer();
        playerOnTurn.startTurn();

        tick();
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

    public Lobby getLobby() {
        return lobby;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    protected abstract boolean onPlayerMove(Player player, int column, int row);

    protected abstract Board createBoard();

    protected abstract ViewOLD<Board> createBoardView(Board board);

    protected abstract void onInit();

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract void onTick();

}
