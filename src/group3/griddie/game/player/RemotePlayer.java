package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.game.tictactoe.TicTacToe;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.network.NetworkMain;
import group3.griddie.network.commands.SendCommandLogin;
import group3.griddie.network.commands.SendCommandMove;
import group3.griddie.network.commands.SendCommandSubscribe;
import group3.griddie.network.networktranslator.NetworkTranslator;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;

public class RemotePlayer extends Player {
    private ArrayList<String> buffer;
    private ArrayList<String> bufferOwn;
    private TicTacToe tictactoe;
    private int tick;
    private Task<Void> sleeper;

    public RemotePlayer(Game game, Actor.Type type, String name, ArrayList<String> buffer, TicTacToe tictactoe) {
        super(game, type, name);

        bufferOwn bufferThread = new bufferOwn(this);
        Thread remotePlayerThread = new Thread(bufferThread);
        remotePlayerThread.start();

        this.buffer = buffer;
        this.bufferOwn = new ArrayList<>();
        this.tictactoe = tictactoe;
        this.tick = 0;

    }

    public ArrayList<String> getBufferOwn() {
        return bufferOwn;
    }

    public ArrayList<String> getBuffer() {
        return tictactoe.getRemotePlayerBuffer();
    }

    @Override
    protected void onInit() {

    }

    private void playerMove() {
        if (bufferOwn.size() > 0) {
            int[] xy = NetworkTranslator.reverseTranslateMove("tic-tac-toe", Integer.valueOf(bufferOwn.get(0)));
            this.getGame().playerMove(this, xy[0], xy[1]);
            bufferOwn.remove(0);
            System.out.println("did move");
        }
    }

    @Override
    protected void onTick() {

    }

    @Override
    protected void onStartTurn() {
        sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println("started remote player");
                while (bufferOwn.size() == 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };


        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                playerMove();
            }
        });

        new Thread(sleeper).start();
    }

    @Override
    protected void onEndTurn() {

    }

    public class bufferOwn implements Runnable {
        private boolean running;
        private RemotePlayer player;

        public bufferOwn(RemotePlayer player) {
            this.player = player;
            this.running = true;
        }

        @Override
        public void run() {
            while (this.running) {
                if (tictactoe.getRemotePlayerBuffer().size() == 1) {
                    System.out.println("check: " + this.player.getBuffer().size());
                    this.player.getBufferOwn().add(player.getBuffer().get(0));
                    tictactoe.getRemotePlayerBuffer().remove(0);
                    System.out.println("not stuck");
                }

            }
        }

        public void setRunning(boolean running) {
            this.running = running;
        }
    }
}
