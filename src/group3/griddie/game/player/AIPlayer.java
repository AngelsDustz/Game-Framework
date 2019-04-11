package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.game.ai.AI;
import group3.griddie.model.board.Cell;
import group3.griddie.model.board.actor.Actor;

public class AIPlayer extends Player {
    private Difficulty difficulty;
    private AI gameAI;


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

    public AI getAI() {
        return this.gameAI;
    }

    public void setGameAI(AI gameAI) {
        this.gameAI = gameAI;
    }

    @Override
    protected void onInit() {
    }

    @Override
    protected void onTick() {
    }

    @Override
    protected void onStartTurn() {
        Cell predicted = this.gameAI.predictMove();

        if (predicted != null) {
            System.out.println("AI move: " + predicted);
            this.getGame().playerMove(this, predicted.getX(), predicted.getY());
        }
    }

    @Override
    protected void onEndTurn() {
    }
}
