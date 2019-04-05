package group3.griddie.game.player;

import group3.griddie.game.Game;

public class AIPlayer extends Player {
    private Difficulty difficulty;

    @Override
    protected void onStartTurn() {

    }

    @Override
    protected void onEndTurn() {

    }

    /**
     * Difficulty ENUMs.
     */
    public static enum Difficulty {
        DIFFICULTY_EASY,
        DIFFICULTY_MEDIUM,
        DIFFICULTY_HARD
    }

    public AIPlayer(Game game, String name) {
        super(game, name);
        this.difficulty = Difficulty.DIFFICULTY_EASY;
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected void onTick() {

    }

    public AIPlayer(Game game, Difficulty difficulty) {
        super(game, "AI");
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
