package group3.griddie.model.board.actor;

import group3.griddie.model.Model;

public abstract class Actor extends Model {
    public enum Type {
        TYPE_1,
        TYPE_2,
    }

    private Type type;

    public Actor(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
