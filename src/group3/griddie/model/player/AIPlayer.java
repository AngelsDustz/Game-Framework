package group3.griddie.model.player;

public class AIPlayer extends Player {
    private Difficulty difficulty;

    /**
     * Difficulty ENUMs.
     */
    public static enum Difficulty {
        DIFFICULTY_EASY,
        DIFFICULTY_MEDIUM,
        DIFFICULTY_HARD
    }

    public AIPlayer() {
        this.difficulty = Difficulty.DIFFICULTY_EASY;

        this.setName("Easy AI");
    }

    public AIPlayer(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.setName("AI");
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
