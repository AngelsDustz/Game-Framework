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
    ArrayList<String> buffer;
    ArrayList<String> bufferOwn;
    TicTacToe tictactoe;
    int tick;

    public RemotePlayer(Game game, Actor.Type type, String name, ArrayList<String> buffer, TicTacToe tictactoe) {
        super(game, type, name);
        this.buffer = buffer;
        this.bufferOwn = new ArrayList<>();
        this.tictactoe = tictactoe;
        this.tick = 0;
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onTick() {

    }

    @Override
    protected void onStartTurn() {
        while (bufferOwn.size() == 0) {
            if (tictactoe.getRemotePlayerBuffer().size() >= 1) {
                bufferOwn.add(tictactoe.getRemotePlayerBuffer().get(0));
                tictactoe.getRemotePlayerBuffer().remove(0);
                System.out.println("not stuck");
            }
            System.out.println(tictactoe.getRemotePlayerBuffer());
        }
        System.out.println("did move");
        int[] xy = NetworkTranslator.reverseTranslateMove("tic-tac-toe", Integer.valueOf(bufferOwn.get(0)));
        this.getGame().playerMove(this, xy[0], xy[1]);
        bufferOwn.clear();
    }

    @Override
    protected void onEndTurn() {

    }

    public class bufferOwn implements Runnable{


        @Override
        public void run() {

        }
    }
}
