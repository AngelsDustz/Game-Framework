package group3.griddie.game;

import group3.griddie.game.player.Player;

public class GameThread extends Thread implements Runnable {

    private Game game;

    public GameThread(Game game) {
        this.game = game;
        this.setDaemon(true);
    }
    public boolean forceStop;

    @Override
    public void run() {
        while (game.isRunning() && !forceStop) {
            Player currentPlayer = game.getActivePlayer();
            currentPlayer.turnEndEvent.addListener(this::touch);

            try {
                System.out.println("Awaiting move by " + currentPlayer.getName());

                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentPlayer.turnEndEvent.removeListener(this::touch);

            if (game.isRunning()) {
                game.setActivePlayer(game.getNextPlayer());
            }
        }
    }

    public void setForceStop() {
        forceStop = true;
    }

    private synchronized void touch() {
        this.notify();
    }

}
