package group3.griddie.model.board;

import group3.griddie.model.Model;
import group3.griddie.model.board.actor.Actor;

public class Cell extends Model {

    private Actor occupant;

    public Cell() {

    }

    public Actor getOccupant() {
        return occupant;
    }

    public void setOccupant(Actor occupant) {
        this.occupant = occupant;
        //NOTIFY OCCUPANT OBSERVER
    }

    public void clear() {
        this.setOccupant(null);
    }

}
