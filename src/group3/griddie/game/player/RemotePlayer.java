package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.network.NetworkMain;
import group3.griddie.network.commands.SendCommandLogin;
import group3.griddie.network.commands.SendCommandMove;
import group3.griddie.network.commands.SendCommandSubscribe;
import group3.griddie.network.networktranslator.NetworkTranslator;

import javax.annotation.processing.SupportedSourceVersion;

public class RemotePlayer extends Player {
    NetworkMain access;
    public RemotePlayer(Game game, Actor.Type type, String name, NetworkMain access) {
        super(game, type, name);
        this.access = access;
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onTick() {

    }

    @Override
    protected void onStartTurn() {
        String current = access.readBufferIn();
        int value = 0;
        if (current != null) {
            System.out.println("remote player" + current);
            if (NetworkTranslator.translateIncomingMessage(current, "player").equals(this.getName())) {
                current = NetworkTranslator.translateIncomingMessage(current, "move");
                value = Integer.valueOf(current);
            } else if (!NetworkTranslator.translateIncomingMessage(current, "player").equals(this.getName())) {
                current = NetworkTranslator.translateIncomingMessage(access.readBufferIn(), "move");
                value = Integer.valueOf(current);
            }

            int[] xy = NetworkTranslator.reverseTranslateMove("tic-tac-toe", value);
            this.getGame().playerMove(this, xy[0], xy[1]);
        }
    }

    @Override
    protected void onEndTurn() {

    }
}
