package group3.griddie.model.board;

import group3.griddie.model.Model;
import group3.griddie.model.board.actor.Actor;

public class Cell extends Model {
    private Actor occupant;
    private boolean disabled;
    private boolean validSpot;
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;

        disabled        = false;
        this.validSpot  = false;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return String.format("Cell @ W:%d H:%d Actor: %s", this.x, this.y, this.occupant);
    }

    public void testOccupant(Actor occupant) {
        this.occupant = occupant;
    }

    public boolean isValidSpot() {
        return validSpot;
    }

    public void setValidSpot(boolean validSpot) {
        this.validSpot = validSpot;

        setChanged();
        notifyObservers();
    }
}
