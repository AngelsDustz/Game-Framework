package group3.griddie.util;

import group3.griddie.model.board.Board;

public class MiniMaxNode {
    private Board           board;
    private MiniMaxNode[]   children;

    public MiniMaxNode(Board board) {
        this.board      = board;
        this.children   = new MiniMaxNode[board.getWidth()*board.getHeight()];

        this.calculateChildren();
    }

    public MiniMaxNode[] getChildren() {
        return children;
    }

    public boolean hasChildren() {
        for (MiniMaxNode node : this.children) {
            if (node != null) {
                return true;
            }
        }

        return false;
    }

    private void calculateChildren() {
        //
    }
}
