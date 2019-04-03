package group3.griddie.model.board;

import group3.griddie.model.Model;
import group3.griddie.model.board.actor.Actor;

public class Cell extends Model {

    private Actor occupant;
    private boolean disabled;

    public Cell() {
        disabled = false;
    }

    @Override
    protected void onTick() {

    }

    public boolean isOccupied() {
        return occupant != null;
    }

    public Actor getOccupant() {
        return occupant;
    }

    public void setOccupant(Actor occupant) {
        this.occupant = occupant;

        setChanged();
        notifyObservers();
    }

    public void clear() {
        this.setOccupant(null);
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
