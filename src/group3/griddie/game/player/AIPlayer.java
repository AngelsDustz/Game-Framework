package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.game.tictactoe.TicTacToeAI;
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


    public AIPlayer(Game game, Actor.Type type, Difficulty difficulty) {
        super(game, type, "AI");
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public TicTacToeAI getTicTacToeAI() {
        if (this.tttAI == null) {
            this.tttAI = new TicTacToeAI(this.getGame(), this);
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
        Cell predicted = this.getTicTacToeAI().predictMove();
        this.getGame().playerMove(this, predicted.getX(), predicted.getY());
    }

    @Override
    protected void onEndTurn() {
    }
}
