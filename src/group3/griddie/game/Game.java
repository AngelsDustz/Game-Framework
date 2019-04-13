package group3.griddie.game;

import group3.griddie.game.player.HumanPlayer;
import group3.griddie.model.board.Board;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.view.game.GameView;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Random;

public abstract class Game extends Scene {

    private Connection connection;
    private Communication communication;
    private Lobby lobby;
    private Player activePlayer;
    private Board board;

    public Game(String game) {
        super(new AnchorPane());

        communication = new Communication(this);
        connection = new Connection(communication);
        lobby = new Lobby(2);
        board = createBoard();
    }

    public final void init() {
        lobby.playerJoinedEvent.addListener(this::onPlayerJoined);
        lobby.allPlayersReadyEvent.addListener(this::start);

        createView();
        onInit();
    }

    private void createView() {
        GameView gameView = new GameView(this);

        AnchorPane root = (AnchorPane) getRoot();
        root.getChildren().add(gameView);
    }

    public void startOnlineGame() {
        connection.connect();

        if (!connection.isConnected()) {
            System.out.println("Not possible to create connection");
        } else {
            HumanPlayer player = new HumanPlayer("Jesse" + new Random().nextInt());
            lobby.join(player);

            connection.login(player);
            connection.subscribe();
        }
    }

    private void onPlayerJoined(Player player) {
        player.setGame(this);
        player.init();

        if (lobby.isFull()) {
            start();
        }
    }

    public final void start() {
        System.out.println("Starting game");

        onStart();
    }

    public void setActivePlayer(Player player) {
        if (activePlayer != null) {
            activePlayer.endTurn();
        }

        this.activePlayer = player;

        player.startTurn();
    }

    public Lobby getLobby() {
        return lobby;
    }
































    public interface PlayerMove {
        void onMove(Player player, int x, int y);
    }


    private boolean started;
    private Player playerOnTurn;
    private String game;
    private int round;
    private GameView gameView;
    private ArrayList<PlayerMove> playerMoveListeners;







    public void addOnPlayerMoveListener(PlayerMove listener) {
        this.playerMoveListeners.add(listener);
    }

    @Override
    public String toString() {
        return game;
    }

    public final void stop() {
        started = false;

        onStop();
    }

    public final void tick() {

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
        return null;
    }

    public void placeActor(Actor actor, int x, int y) {
        Cell cell = board.getCell(x, y);
        cell.setOccupant(actor);
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
