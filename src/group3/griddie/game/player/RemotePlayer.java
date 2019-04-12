package group3.griddie.game.player;

import com.sun.security.ntlm.Server;
import group3.griddie.game.Game;
import group3.griddie.model.board.actor.Actor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class RemotePlayer extends Player implements Runnable {

    private Thread thread;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ServerResponse root;

    public RemotePlayer() {
        thread = new Thread(this);

        ServerResponse gameSR = new ServerResponse("GAME");
        gameSR.addSub(new ServerResponse("MOVE", this::handleMove));
        gameSR.addSub(new ServerResponse("YOURTURN", this::handleYourTurn));
        gameSR.addSub(new ServerResponse("MATCH", this::handleMatch));

        ServerResponse svrSR = new ServerResponse("SVR");
        svrSR.addSub(gameSR);

        root = new ServerResponse("");
        root.addSub(svrSR);
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
        while (true) {
            String line = "";

            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] words = line.split(" ");

            ServerResponse current = root;
            int index = 0;
            for (String w : words) {
                index++;
                for (ServerResponse sr : current.getSubs()) {
                    if (sr.getCommand().equals(w)) {
                        current = sr;

                        StringBuilder data = new StringBuilder();

                        for (int i = index; i < words.length; i++) {
                            data.append(words[i]).append(" ");
                        }

                        current.excecute(data.toString());
                    }
                }
            }
        }
    }

    public void handleMatch(Map data) {
        System.out.println("HANDLE MATCH");
    }

    private void handleMove(Map data) {
        System.out.println("HANDLE MOVE");

        System.out.println(data.get("MOVE") + " <---");

        if (isOnTurn()) {
            //getGame().playerMove(this, );
        }
    }

    private void handleYourTurn(Map data) {
        System.out.print("HANDLE YOURTURN");
    }

}
