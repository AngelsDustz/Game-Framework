package group3.griddie.game;

import group3.griddie.game.ai.TicTacToeAI;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.server.Communication;
import group3.griddie.game.server.Connection;
import group3.griddie.model.board.Board;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.util.event.Event;
import group3.griddie.util.event.ArgEvent;
import group3.griddie.view.game.GameView;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public abstract class Game extends Scene {

    public final ArgEvent<Move> onMove;
    public final Event onStart;
    private String name;
    private Connection connection;
    private Communication communication;
    private Lobby lobby;
    private Player activePlayer;
    private Board board;
    private GameThread thread;
    private boolean started;

    public Game(String name) {
        super(new AnchorPane());

        this.name = name;

        onMove = new ArgEvent<>();
        onStart = new Event();

        connection = new Connection();
        communication = new Communication(this, connection);

        lobby = new Lobby(2);
        board = createBoard();
        thread = new GameThread(this);
    }

    public final void init() {
        lobby.playerJoinedEvent.addListener(this::onPlayerJoined);

        createView();
        onInit();
    }

    private void createView() {
        GameView gameView = new GameView(this);

        AnchorPane root = (AnchorPane) getRoot();
        root.getChildren().add(gameView);
    }

    public void startHumanVsRemote() {
        connection.connect();

        if (!connection.isConnected()) {
            System.out.println("Not possible to create connection");
        } else {
            HumanPlayer player = new HumanPlayer("SFC " + new Random().nextInt());
            lobby.join(player);

            connection.connect();
            connection.login(player);
        }
    }

    public void startAiVsRemote() {
        connection.connect();

        if (!connection.isConnected()) {
            System.out.println("Not possible to create connection");
        } else {
            Player player = createAiPlayer();
            lobby.join(player);

            connection.connect();
            connection.login(player);
        }
    }

    public void startAiGame() {
        HumanPlayer player = new HumanPlayer("Jesse" + new Random().nextInt());
        lobby.join(player);

        AIPlayer aiPlayer = new AIPlayer("AI", AIPlayer.Difficulty.DIFFICULTY_HARD);
        aiPlayer.setGameAI(new TicTacToeAI(this, aiPlayer));
        lobby.join(aiPlayer);

        setActivePlayer(player);
    }

    private void onPlayerJoined(Player player) {
        player.setGame(this);
        player.init();
    }

    public final void start() {
        System.out.println("Starting game");

        activePlayer.setActorType(Actor.Type.TYPE_1);
        getNextPlayer().setActorType(Actor.Type.TYPE_2);

        onStart();

        started = true;

        thread.start();
        onStart.call();
    }

    public final void stop() {
        started = false;

        System.out.println("Game ended");

        onStop();
    }

    public void setActivePlayer(Player player) {
        this.activePlayer = player;

        if (!started && lobby.isFull()) {
            start();
        }

        player.startTurn();
    }

    public void playerMove(Player player, int x, int y) {
        if (moveIsValid(player, x, y)) {
            onPlayerMove(player, x, y);

            Move move = new Move(player, x, y);

            onMove.call(move);

            activePlayer.endTurn();

            if (checkWin(move)) {
                System.out.println(player.getName() + " wins the game!");
                stop();
            }
        }
    }

    public Lobby getLobby() {
        return lobby;
    }

    public Player getNextPlayer() {
        Player[] players = lobby.getPlayers();

        for (int i = 0; i < players.length; i++) {
            if (players[i] == activePlayer) {
                return players[(i + 1) % players.length];
            }
        }

        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public Communication getCommunication() {
        return communication;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public String getName() {
        return name;
    }

    public boolean isRunning() {
        return this.started;
    }

    public abstract boolean moveIsValid(Player player, int x, int y);

    public abstract boolean checkWin(Move move);

    protected abstract void onPlayerMove(Player player, int column, int row);

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public abstract boolean canDoTurn(Player player);

    protected abstract Board createBoard();

    protected abstract void onInit();

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract void onTick();

    protected abstract AIPlayer createAiPlayer();

}
