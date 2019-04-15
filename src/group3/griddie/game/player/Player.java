package group3.griddie.game.player;

import group3.griddie.game.Entity;
import group3.griddie.game.Game;
import group3.griddie.model.board.actor.Actor;
import group3.griddie.util.event.Event;

import java.util.ArrayList;

public abstract class Player extends Entity {

    public final Event turnEndEvent;
    public final Event turnStartEvent;

    private String name;
    private boolean onTurn;
    private Game game;

    public Player(String name) {
        this.name = name;
        turnEndEvent = new Event();
        turnStartEvent = new Event();
    }

    public void init() {
        System.out.println(name + " initialized");

        onInit();
    }

    public void startTurn() {
        System.out.println(name + " turn started");

        onTurn = true;

        onStartTurn();

        turnStartEvent.call();
    }

    public void endTurn() {
        if (!isOnTurn()) {
            return;
        }

        System.out.println(name + " turn ended");

        onTurn = false;

        onEndTurn();
        this.setChanged();
        this.notifyObservers();
        turnEndEvent.call();
    }





















    private ArrayList<Actor> actors;
    private Actor.Type actorType;

    public Player(Game game, Actor.Type type, String name) {
        this.game = game;
        actors = new ArrayList<>();
        this.name = name;
        this.actorType = type;
        turnEndEvent = new Event();
        turnStartEvent = new Event();
    }

    public void tick() {
        //System.out.println(name + " ticked");

        onTick();
    }

    public ArrayList<Actor> getActors() {
        return this.actors;
    }


    public Actor registerActor(Actor actor) {
        //actors.add(actor);
        return null;
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

    public Actor.Type getActorType() {
        return actorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setActorType(Actor.Type actorType) {
        this.actorType = actorType;
    }

    protected abstract void onStartTurn();
    protected abstract void onEndTurn();
    protected abstract void onInit();
    protected abstract void onTick();
}
