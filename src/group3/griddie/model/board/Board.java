package group3.griddie.model.board;

public class Board {

    private int width;
    private int height;
    private Grid grid;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Grid(width, height);
    }

}
