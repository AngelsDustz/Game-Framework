package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.game.tictactoe.TicTacToe;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.network.NetworkMain;
import group3.griddie.network.commands.SendCommandLogin;
import group3.griddie.network.commands.SendCommandMove;
import group3.griddie.network.commands.SendCommandSubscribe;
import group3.griddie.network.networktranslator.NetworkTranslator;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;

public class RemotePlayer extends Player {
    private ArrayList<String> buffer;
    private ArrayList<String> bufferOwn;
    private TicTacToe tictactoe;
    private int tick;

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

    @Override
    protected void onTick() {

    }

    @Override
    protected void onStartTurn() {
        while(bufferOwn.size() == 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(bufferOwn.size() != 0) {
            int[] xy = NetworkTranslator.reverseTranslateMove("tic-tac-toe", Integer.valueOf(bufferOwn.get(0)));
            this.getGame().playerMove(this, xy[0], xy[1]);
            bufferOwn.clear();
        }
    }

    @Override
    protected void onEndTurn() {

    }

    public class bufferOwn implements Runnable{
        private boolean running;
        private RemotePlayer player;

        public bufferOwn(RemotePlayer player){
            this.player = player;
            this.running = true;
        }

        @Override
        public void run() {
            while (this.running) {
                if(this.player.getBufferOwn().size() == 0) {
                    System.out.println("check: " + this.player.getBuffer().size());
                    if (this.player.getBuffer().size() >= 1) {
                        this.player.getBufferOwn().add(player.getBuffer().get(0));
                        this.player.getBuffer().remove(0);
                        System.out.println("not stuck");
                    }
                }
            }
        }

        public void setRunning(boolean running) {
            this.running = running;
        }
    }
}
