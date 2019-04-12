package group3.griddie.game.player;

import group3.griddie.game.Game;
import group3.griddie.model.board.actor.Actor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RemotePlayer extends Player implements Runnable {

    private Thread thread;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public RemotePlayer() {
        thread = new Thread(this);
    }

    @Override
    protected void onInit() {
        try {
            socket = new Socket("localhost", 7789);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        thread.start();

        out.println("login jesse");
        out.println("subscribe Tic-tac-toe");
    }

    @Override
    protected void onTick() {

    }

    @Override
    protected void onStartTurn() {

    }

    @Override
    protected void onEndTurn() {

    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
