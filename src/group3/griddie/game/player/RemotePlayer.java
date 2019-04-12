package group3.griddie.game.player;

import group3.griddie.network.networktranslator.NetworkTranslator;

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

//        getGame().addOnPlayerMoveListener(((Player player, int x, int y) -> {
//            if (player != this) {
//                sendOpponentMove(x, y);
//            }
//        }));

        thread.start();

        out.println("login " + getName());
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

    public void handleMatch(Map<String, String> data) {
        this.setReady(true);
    }

    private void handleMove(Map<String, String> data) {
        if (!data.get("PLAYER").equals(getName())) {
            startTurn();
        }

        if (isOnTurn()) {
            int move = Integer.parseInt(data.get("MOVE"));

            System.out.println("Received move " + move);

            int x = move % getGame().getBoard().getWidth();
            int y = move / getGame().getBoard().getWidth();

            getGame().playerMove(this, x, y);

            endTurn();

            this.getGame().getLobby().getPlayer(0).startTurn();
        } else {
            System.out.println("ERROR");
            System.out.println(data);
        }
    }

    private void handleYourTurn(Map<String, String> data) {

    }

    private void sendOpponentMove(int x, int y) {
        int move = NetworkTranslator.translateMove(0, "tic-tac-toe", x, y);
        out.println("MOVE " + move);
    }

}
