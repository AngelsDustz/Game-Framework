package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.model.board.actor.Actor;

import java.util.ArrayList;

public abstract class Player {

    private boolean onTurn;
    private Game game;
    private ArrayList<Actor> actors;

    public Player(Game game) {
        this.game = game;
        actors = new ArrayList<>();
    }

    public void init() {
        onInit();
    }

    protected abstract void onInit();

    public void registerActor(Actor actor) {
        actors.add(actor);
    }

    public void unregisterActor(Actor actor) {
        actors.remove(actor);
    }

    public void startTurn() {
        onTurn = true;
    }

    public void endTurn() {
        onTurn = false;
    }

    public boolean isOnTurn() {
        return onTurn;
    }

    public Game getGame() {
        return game;
    }

    protected abstract void onStartTurn();
    protected abstract void onEndTurn();

}
