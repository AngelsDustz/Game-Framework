package group3.griddie.game;

import group3.griddie.controller.board.BoardController;
import group3.griddie.model.board.Board;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.player.Player;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public abstract class Game extends Scene {

    private Board board;
    private boolean started;
    private ArrayList<Player> players;
    private Player playerOnTurn;

    public Game() {
        super(new BorderPane());

        players = new ArrayList<>();

        //JUST FOR TESTING
        addPlayer(new HumanPlayer(this));
        addPlayer(new HumanPlayer(this));
    }

    public final void init() {
        board = createBoard();

        BorderPane root = (BorderPane) getRoot();
        root.setCenter(new BoardController(board).getView().getNode());

        for (Player player : players) {
            player.init();
        }

        onInit();
    }

    public final void start() throws Exception {
        if (players.size() <= 1) {
            throw new Exception("Need two players to start a game!");
        }

        started = true;

        playerOnTurn = players.get(0);
        playerOnTurn.startTurn();

        onStart();
    }

    public final void stop() {
        started = false;

        onStop();
    }

    public final void tick() {
        playerOnTurn = getNextPlayer();
        onTick();
    }

    public Board getBoard() {
        return board;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayerOnTurn() {
        return playerOnTurn;
    }

    private Player getNextPlayer() {
        int index = players.indexOf(playerOnTurn);

        return index >= players.size() - 1 ? players.get(0) : players.get(index + 1);
    }

    public void playerMove(Player player, int column, int row) {
        if (playerOnTurn != player) {
            return;
        }

        if (onPlayerMove(player, column, row)) {
            tick();
        }
    }

    protected abstract boolean onPlayerMove(Player player, int column, int row);

    protected abstract Board createBoard();

    protected abstract void onInit();

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract void onTick();

}
