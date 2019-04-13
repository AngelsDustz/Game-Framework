package group3.griddie.game;

import group3.griddie.game.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection implements Runnable {

    private Thread thread;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean connected;
    private Communication communication;

    public Connection(Communication communication) {
        this.communication = communication;
        thread = new Thread(this);
    }

    public void connect() {
        System.out.println("Connecting to server");

        try {
            socket = new Socket("localhost", 7789);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            connected = true;

            thread.start();
        } catch (IOException e) {
            connected = false;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String input = in.readLine();

                System.out.println(input);

                communication.handle(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void login(Player player) {
        out.println("login " + player.getName());
    }

    public void subscribe() {
        out.println("subscribe " + "Tic-tac-toe");
    }

}
