package group3.griddie.game.tictactoe.tictactoeai;

public class CellSimulated{
    public String MinOrMax;
    public boolean disabled;

    public int x;
    public int y;

    public CellSimulated(int c, int r) {
        this.x = c;
        this.y = r;
        this.MinOrMax = " ";
        disabled = false;
    }

    public String getMinOrMax() {
        return MinOrMax;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setOccupant(String occupant) {
        this.MinOrMax = occupant;
        setDisabled(true);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

