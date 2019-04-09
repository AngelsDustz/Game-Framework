package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.network.NetworkMain;
import group3.griddie.network.commands.SendCommandLogin;
import group3.griddie.network.commands.SendCommandMove;
import group3.griddie.network.commands.SendCommandSubscribe;

public class RemotePlayer extends Player {

    public RemotePlayer(Game game, Actor.Type type, String name, NetworkMain access) {
        super(game, type, name, access);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onTick() {

    }

    @Override
    protected void onStartTurn() {

    }

    @Override
    protected void onEndTurn() {

    }
}
