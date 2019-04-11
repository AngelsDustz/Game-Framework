package group3.griddie.game.tictactoe;

import group3.griddie.game.Game;
import group3.griddie.game.player.AIPlayer;
import group3.griddie.game.player.HumanPlayer;
import group3.griddie.game.player.Player;
import group3.griddie.game.player.RemotePlayer;
import group3.griddie.model.board.Board;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.model.board.actor.TicTacToeActor;
import group3.griddie.network.NetworkHelperThread;
import group3.griddie.network.NetworkMain;
import group3.griddie.network.bufferThread;
import group3.griddie.network.commands.*;
import group3.griddie.network.invoker.CommandInvoker;
import group3.griddie.network.networktranslator.NetworkTranslator;
import group3.griddie.viewOLD.ViewOLD;
import group3.griddie.viewOLD.board.tictactoe.TicTacToeBoardViewOLD;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;


public class TicTacToe extends Game {
    private static String IP = "127.0.0.1";
    private static int PORT = 7789;
    private String ourplayerName = "TRUMP";
    private NetworkHelperThread runner = new NetworkHelperThread(IP, PORT);
    private Thread NetworkThread = new Thread(runner);
    private NetworkMain access = runner.getNetworkRunner();
    private boolean networkOn = true;

    private ArrayList<String> homePlayerBuffer = new ArrayList<>();
    private ArrayList<String> remotePlayerBuffer = new ArrayList<>();
    private ArrayList<String> otherBuffer = new ArrayList<>();
    private volatile Boolean serverNetworkWinOrLoss = null;
    private bufferThread networkSetupAccess = new bufferThread(this, access,ourplayerName, serverNetworkWinOrLoss);
    private Thread networkSetupThread = new Thread(networkSetupAccess);

    private GetInformation information = new GetInformation(access);
    private GetAllInformation allInformation = new GetAllInformation(access);
    private SendCommandMove move = new SendCommandMove(access, 0);
    private GetBufferSize bufferSize = new GetBufferSize(access);
    private CommandInvoker invoker = new CommandInvoker();


    private ArrayList<Cell> alreadySendMoves = new ArrayList<>();


    public TicTacToe(String game) {
        super(game);
        NetworkThread.start();
        setupNetwork();
    }

    public void setNetworkOn(boolean networkOn) {
        this.networkOn = networkOn;
    }

    public boolean getNetworkOn() {
        return networkOn;
    }

    public ArrayList<String> getRemotePlayerBuffer() {
        return remotePlayerBuffer;
    }

    public ArrayList<String> getHomePlayerBuffer() {
        return homePlayerBuffer;
    }

    public String getOurplayerName() {
        return ourplayerName;
    }

    public Boolean getServerNetworkWinOrLoss() {
        return serverNetworkWinOrLoss;
    }

    public ArrayList<String> getOtherBuffer() {
        return otherBuffer;
    }

    public void setServerNetworkWinOrLoss(Boolean serverNetworkWinOrLoss) {
        this.serverNetworkWinOrLoss = serverNetworkWinOrLoss;
    }

    public NetworkMain getAccess() {
        return this.access;
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
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(networkSetupAccess.getOutPlayer() == null && networkSetupAccess.getCheck() < 2){
                    try {
                        Thread.sleep(50);
                    }

                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };

        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                setupGame();
            }
        });
        new Thread(sleeper).start();
        //lobby.join(new HumanPlayer(this, Actor.Type.TYPE_1, "h"));
        //lobby.join(new HumanPlayer(this, Actor.Type.TYPE_2, "a"));
    }

    public void setupGame(){
        AIPlayer aiPlayer = null;
        RemotePlayer remotePlayer = null;
        HumanPlayer player = null;

        player = new HumanPlayer(this, Actor.Type.TYPE_1, ourplayerName);
        remotePlayer = new RemotePlayer(this, Actor.Type.TYPE_2, networkSetupAccess.getOutPlayer(), remotePlayerBuffer, this);

        if (networkSetupAccess.getCheck() == 2){
            System.out.println("You are the first player");
            lobby.join(remotePlayer);
            lobby.join(player);
        }

        else if(networkSetupAccess.getCheck() == 3){
            System.out.println("You are the second player");
            lobby.join(player);
            lobby.join(remotePlayer);
        }
    }

    private synchronized void setupNetwork() {
        if (getNetworkOn()) {
            ArrayList<String> returnList = waiterAll(invoker);
            for (String string : returnList) {
                System.out.println("return command: " + string);
            }
            SendCommandLogin login = new SendCommandLogin(access, ourplayerName);
            SendCommandSubscribe subscribe = new SendCommandSubscribe(access, this.getGame());
            invoker.executeCommand(login);
            invoker.executeCommand(subscribe);
            networkSetupThread.start();
        }
    }

    private String waiter(CommandInvoker invoker) {
        invoker.executeCommand(bufferSize);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (bufferSize.getBufferSize() == 0) {
            invoker.executeCommand(bufferSize);
        } else if (bufferSize.getBufferSize() > 0) {
            System.out.println("Buffer size: " + bufferSize.getBufferSize());
            return access.readBufferIn();
        }

        return waiter(invoker);
    }

    private ArrayList<String> waiterAll(CommandInvoker invoker) {
        invoker.executeCommand(bufferSize);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (bufferSize.getBufferSize() == 0) {
            invoker.executeCommand(bufferSize);
        } else if (bufferSize.getBufferSize() > 0) {
            System.out.println("Buffer size: " + bufferSize.getBufferSize());
            return access.printAll();
        }

        return waiterAll(invoker);
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
        ArrayList<Cell> cellsArray = board.getCellsArray();

        if (this.checkIfWon(board) != null) {
            this.stop();
        }

        if (networkOn) {
            for (int i = 0; i < cellsArray.size(); i++) {
                if (cellsArray.get(i).isDisabled()) {
                    if (alreadySendMoves.size() != 0) {
                        for (int b = 0; b < alreadySendMoves.size(); b++) {
                            if (cellsArray.get(i).getX() != alreadySendMoves.get(b).getX() && cellsArray.get(i).getX() != alreadySendMoves.get(b).getY()
                                    && cellsArray.get(i).getOccupant().getType() == Actor.Type.TYPE_1) {
                                sendMove(cellsArray, i);
                            }
                        }
                    } else if (alreadySendMoves.size() == 0 && cellsArray.get(i).getOccupant().getType() == Actor.Type.TYPE_1) {
                        sendMove(cellsArray, i);
                    }
                }
            }
        }
        System.out.println("tick tictactoe");
    }

    private void sendMove(ArrayList<Cell> cellsArray, int i) {
        Cell send = cellsArray.get(i);
        int moveNumber = NetworkTranslator.translateMove(0, "tic-tac-toe", send.getX(), send.getY());
        move.setMove(moveNumber);
        invoker.executeCommand(move);
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
        for (int i = 0; i < board.getWidth(); i++) {
            Cell toCheck = board.getCell(i, 0);
            TicTacToeActor toActor = (TicTacToeActor) toCheck.getOccupant();
            boolean check = true;
            if (toActor == null) {
                continue;
            }

            for (int c = 1; c < board.getHeight(); c++) {
                Cell nextCheck = board.getCell(i, c);
                TicTacToeActor nextActor = (TicTacToeActor) nextCheck.getOccupant();
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
        for (int i = 0; i < board.getWidth(); i++) {
            Cell toCheck = board.getCell(0, i);
            TicTacToeActor toActor = (TicTacToeActor) toCheck.getOccupant();
            boolean check = true;
            if (toActor == null) {
                continue;
            }

            for (int c = 1; c < board.getHeight(); c++) {
                Cell nextCheck = board.getCell(c, i);
                TicTacToeActor nextActor = (TicTacToeActor) nextCheck.getOccupant();
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
        Cell cell = board.getCell(1, 1);
        Cell topLeft = board.getCell(0, 0);
        Cell topRight = board.getCell(2, 0);
        Cell botLeft = board.getCell(0, 2);
        Cell botRight = board.getCell(2, 2);

        TicTacToeActor actor = (TicTacToeActor) cell.getOccupant();
        TicTacToeActor topLeftActor = (TicTacToeActor) topLeft.getOccupant();
        TicTacToeActor botRightActor = (TicTacToeActor) botRight.getOccupant();

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

        TicTacToeActor topRightActor = (TicTacToeActor) topRight.getOccupant();
        TicTacToeActor botLeftActor = (TicTacToeActor) botLeft.getOccupant();

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
