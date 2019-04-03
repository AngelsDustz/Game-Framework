package group3.griddie.model.board.actor;

public class TicTacToeActor extends Actor {

    public enum Type {
        O,
        X,
    }

    private Type type;

    public TicTacToeActor(Type type, int x, int y) {
        super(x, y);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
