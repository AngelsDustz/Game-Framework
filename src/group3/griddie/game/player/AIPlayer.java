package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.game.tictactoe.tictactoeai.TicTacToeAI;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;

public class AIPlayer extends Player {

    private Difficulty difficulty;
    private TicTacToeAI tttAI;

    /**
     * Difficulty ENUMs.
     */
    public static enum Difficulty {
        DIFFICULTY_EASY,
        DIFFICULTY_MEDIUM,
        DIFFICULTY_HARD
    }

    public AIPlayer(Game game, Actor.Type type, String name) {
        super(game, type, name);
        this.difficulty = Difficulty.DIFFICULTY_EASY;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public TicTacToeAI getTicTacToeAI() {
        System.out.println("ACTOR: " + this.getActorType());
        if (this.tttAI == null) {
            this.tttAI = new TicTacToeAI(this.getGame(), this, this.getActorType());
        }

        return this.tttAI;
    }

    @Override
    protected void onInit() {
    }

    @Override
    protected void onTick() {

    }

    @Override
    protected void onStartTurn() {
        Cell cell = this.getTicTacToeAI().calculateMinMaxTree();
        this.getGame().playerMove(this, cell.getX(), cell.getY());
    }

    @Override
    protected void onEndTurn() {
    }
}
