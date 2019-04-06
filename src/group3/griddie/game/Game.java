package group3.griddie.game;

import group3.griddie.controller.board.BoardController;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.model.board.Board;
import group3.griddie.game.player.Player;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.view.View;
import group3.griddie.view.game.PlayerView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;

public abstract class Game extends Scene {

    private Board board;
    private boolean started;
    private ArrayList<Player> players;
    private Player playerOnTurn;
    private final GameThread thread;

    public Game() {
        super(new BorderPane());

        players = new ArrayList<>();

        thread = new GameThread(this);

        addPlayer(new HumanPlayer(this, Actor.Type.TYPE_1, "Player 1"));
        addPlayer(new HumanPlayer(this, Actor.Type.TYPE_2, "Player 2"));
    }

    public final void init() {
        BorderPane root = (BorderPane) getRoot();

        board = createBoard();
        View<Board> boardView = createBoardView(board);
        boardView.init();
        boardView.setController(new BoardController(board));

        root.setCenter(boardView.getNode());

        BorderPane bottom = new BorderPane();
        PlayerView player1View = new PlayerView(players.get(0));
        PlayerView player2View = new PlayerView(players.get(1));

        player1View.init();
        player2View.init();

        bottom.setLeft(player1View.getNode());
        bottom.setRight(player2View.getNode());

        root.setBottom(bottom);

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

        thread.start();
    }

    public final void stop() {
        started = false;

        onStop();
    }

    public final void tick() {
        onTick();

        playerOnTurn = getNextPlayer();
        playerOnTurn.startTurn();
    }

    public void playerMove(Player player, int column, int row) {
        if (playerOnTurn != player) {
            return;
        }

        if (onPlayerMove(player, column, row)) {
            player.endTurn();
        }
    }

    public Board getBoard() {
        return board;
    }

    public void addPlayer(Player player) {
        players.add(player);

        player.endTurnEvent.addListener(thread::touch);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayerOnTurn() {
        return playerOnTurn;
    }

    private Player getNextPlayer() {
        int index = playerOnTurn == null ? -1 : players.indexOf(playerOnTurn);
        return index >= players.size() - 1 ? players.get(0) : players.get(index + 1);
    }

    public void placeActor(Actor actor, int x, int y) {
        Cell cell = board.getCell(x, y);
        cell.setOccupant(actor);
    }

    public boolean isRunning() {
        return this.started;
    }

    protected abstract boolean onPlayerMove(Player player, int column, int row);

    protected abstract Board createBoard();

    protected abstract View<Board> createBoardView(Board board);

    protected abstract void onInit();

    protected abstract void onStart();

    protected abstract void onStop();

    protected abstract void onTick();

}
