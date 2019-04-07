package group3.griddie.game;

import group3.griddie.controller.board.BoardController;
import group3.griddie.model.board.Board;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.view.View;
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
    }

    public final void init() {
        BorderPane root = (BorderPane) getRoot();

        board = createBoard();
        View<Board> boardView = createBoardView(board);
        boardView.init();
        boardView.setController(new BoardController(board));

        root.setCenter(boardView.getNode());

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

        onStart();

        nextTurn();
    }

    public final void stop() {
        started = false;

        onStop();
    }

    public final void tick() {
        for (Player player : players) {
            player.tick();
        }

        onTick();
    }

    public void nextTurn() {
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
        int index = playerOnTurn == null ? 0 : players.indexOf(playerOnTurn);
        return index >= players.size() - 1 ? players.get(0) : players.get(index + 1);
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

    protected abstract View<Board> createBoardView(Board board);

    protected abstract void onInit();

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract void onTick();

}
