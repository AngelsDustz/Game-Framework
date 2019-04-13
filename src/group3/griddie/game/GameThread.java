package group3.griddie.game;

import group3.griddie.game.player.Player;

public class GameThread extends Thread implements Runnable {

    private Game game;

    public GameThread(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (true) {
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

            game.setActivePlayer(game.getNextPlayer());
        }
    }

    private synchronized void touch() {
        this.notify();
    }

}
