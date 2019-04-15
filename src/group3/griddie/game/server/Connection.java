package group3.griddie.game.server;

import group3.griddie.Griddie;
import group3.griddie.game.player.Player;
import group3.griddie.util.event.ArgEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Connection implements Runnable {

    public final ArgEvent<String> inputEvent;

    private Thread thread;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean connected;

    public Connection() {
        inputEvent = new ArgEvent<>();

        thread = new Thread(this);
    }

    public void connect() {
        if (connected) {
            return;
        }

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

                //System.out.println(input);

                synchronized (this) {
                    inputEvent.call(input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String data) {
        out.println(data);
    }

    public boolean isConnected() {
        return connected;
    }

    public void login() {
        out.println("login " + Griddie.NAME);
    }

    public void subscribe(String game) {
        out.println("subscribe " + game);
    }

    public void fetchPlayerList() {
        out.println("get playerlist");
    }

    public void challenge(String name, String game) {
        out.println("challenge \"" + name + "\" " + " \"" + game + "\"");
    }

    public void logout() {
        out.println("logout");
    }

    public void challengeAccept(int num) {
        out.println("challenge accept " + num);
    }

}
