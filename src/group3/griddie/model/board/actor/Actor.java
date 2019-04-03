package group3.griddie.model.board.actor;

import group3.griddie.model.Model;

public abstract class Actor extends Model {
    private int x;
    private int y;

    public Actor(int x, int y) {
        setPosition(x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
