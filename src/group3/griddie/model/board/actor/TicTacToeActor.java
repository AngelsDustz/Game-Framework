package group3.griddie.model.board.actor;

public class TicTacToeActor extends Actor {

    public enum Type {
        O,
        X,
    }

    private Type type;

    public TicTacToeActor(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
