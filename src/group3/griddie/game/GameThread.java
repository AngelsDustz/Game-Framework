package group3.griddie.game;

public class GameThread extends Thread{

    private Game game;

    public GameThread(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        synchronized (this) {
            while(game.isRunning()) {
                game.tick();

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
