package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.model.board.actor.Actor;

import java.util.ArrayList;

public abstract class Player {

    private boolean onTurn;
    private Game game;
    private ArrayList<Actor> actors;
    private String name;

    public Player(Game game, String name) {
        this.game = game;
        actors = new ArrayList<>();
        this.name = name;
    }

    public void init() {
        System.out.println(name + " initialized");

        onInit();
    }

    public void tick() {
        System.out.println(name + " ticked");

        onTick();
    }

    public void startTurn() {
        System.out.println(name + " turn started");

        onTurn = true;

        onStartTurn();
    }

    public void endTurn() {
        System.out.println(name + " turn ended");

        onTurn = false;

        onEndTurn();
    }

    public ArrayList<Actor> getActors() {
        return this.actors;
    }


    public void registerActor(Actor actor) {
        actors.add(actor);
    }

    public void unregisterActor(Actor actor) {
        actors.remove(actor);
    }

    public boolean isOnTurn() {
        return onTurn;
    }

    public Game getGame() {
        return game;
    }

    protected abstract void onStartTurn();
    protected abstract void onEndTurn();
    protected abstract void onInit();
    protected abstract void onTick();

}
