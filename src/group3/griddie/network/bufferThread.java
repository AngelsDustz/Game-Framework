package group3.griddie.network;

import com.sun.org.apache.xpath.internal.operations.Bool;
import group3.griddie.game.tictactoe.TicTacToe;
import group3.griddie.network.NetworkMain;

import java.util.ArrayList;
import java.util.Iterator;

public class bufferThread implements Runnable {
    private ArrayList<String> homePlayer;
    private ArrayList<String> remotePlayerBuffer;
    private ArrayList<String> other;
    private ArrayList<String> calculatedDataBuffer;
    private TicTacToe accessToBuffers;

    private Boolean runnning = true;
    private Boolean setup = true;
    private int check;
    private NetworkMain access;
    private String ourPlayer;
    private String outPlayer;
    private Boolean win;
    private int confirmation;

    public bufferThread(TicTacToe ticTacToe, NetworkMain access, String ourPlayer, Boolean win) {

        this.homePlayer = ticTacToe.getHomePlayerBuffer();
        this.other = ticTacToe.getOtherBuffer();
        this.calculatedDataBuffer = new ArrayList<>();
        this.remotePlayerBuffer = ticTacToe.getRemotePlayerBuffer();
        this.access = access;
        this.ourPlayer = ourPlayer;
        this.outPlayer = null;
        this.win = win;
        this.check = 0;
        this.confirmation = 0;
        this.accessToBuffers = ticTacToe;

    }


    @Override
    public void run() {
        while (setup) {
            setupMatch();
        }
        while (runnning) {
            putInBuffers();
        }
    }

    public void setRunning(boolean set) {
        this.runnning = set;
    }

    public Boolean getSetup() {
        return this.setup;
    }

    public String getOutPlayer() {
        return outPlayer;
    }

    public int getCheck() {
        return this.check;
    }

    private void setupMatch() {
        String incomingDataBuffer = access.readBufferIn();
        if (incomingDataBuffer != null) {
            System.out.println(incomingDataBuffer);
            System.out.println("buffer thread incomingdataBuffer: " + incomingDataBuffer);
            calculatedDataBuffer.add(incomingDataBuffer);
        }
        if (incomingDataBuffer == null && check == 1) {
            check = check + 2;
            System.out.println("setup completed");
            setup = false;
        }

        String remove = null;
        Iterator<String> get = calculatedDataBuffer.iterator();
        while (get.hasNext()){
            String info = get.next();
            String[] splitInfo = info.split("\\{");
            splitInfo[0] = splitInfo[0].trim();
            if (splitInfo[0].equals("OK")){
                confirmation = confirmation + 1;
                if (confirmation > 0){
                    System.out.println("SERVER OK");
                }
                get.remove();
            }
            if (splitInfo[0].equals("SVR GAME MATCH") && confirmation == 2) {
                System.out.println("'" + splitInfo[0] + "'");
                check = check + 1;
                splitInfo[1].replaceAll("\\}", "");
                String[] tripleSplit = splitInfo[1].split(",");
                for (int i = 0; i < tripleSplit.length; i++) {
                    tripleSplit[i] = tripleSplit[i].replaceAll("\\}", "");
                    tripleSplit[i] = tripleSplit[i].replaceAll("\"", "");
                    tripleSplit[i] = tripleSplit[i].trim();
                }

                String[] opponent = tripleSplit[2].split(":");

                for (int i = 0; i < opponent.length; i++) {
                    opponent[i] = opponent[i].trim();
                }

                System.out.println("opponent: " + opponent[1]);
                System.out.println("check value: " + check);
                this.outPlayer = opponent[1];
                get.remove();

            } else if (splitInfo[0].equals("SVR GAME YOURTURN") && check == 1) {
                check = check + 1;
                System.out.println("setup completed");
                setup = false;
            }
        }
        if (remove != null) {
            calculatedDataBuffer.remove(remove);
        }
    }

    private void putInBuffers() {
        String incomingDataBuffer = access.readBufferIn();
        if (incomingDataBuffer != null) {
            calculatedDataBuffer.add(incomingDataBuffer);
            System.out.println(incomingDataBuffer);
        }
        String remove = null;
        if (calculatedDataBuffer.size() > 0) {
            Iterator<String> get = calculatedDataBuffer.iterator();
            while (get.hasNext()){
                String info = get.next();
                String[] splitInfo = info.split("\\{");
                splitInfo[0] = splitInfo[0].trim();
                if (splitInfo[0].equals("SVR GAME MOVE")) {

                    String[] tplitInfo = splitInfo[1].split(",");
                    for (int i = 0; i < tplitInfo.length; i++) {
                        tplitInfo[i] = tplitInfo[i].replaceAll("\"", "");
                        tplitInfo[i] = tplitInfo[i].replaceAll("\\}", "");
                        tplitInfo[i] = tplitInfo[i].trim();

                    }

                    String[] playerName = tplitInfo[0].split(":");
                    System.out.println("playername: " + playerName[1]);
                    String[] playerMove = tplitInfo[1].split(":");
                    System.out.println("playermove: " + playerMove[1]);
                    String[] playerDetail = tplitInfo[2].split(":");

                    for (int i = 0; i < playerName.length; i++) {
                        playerName[i] = playerName[i].replaceAll("\"", "");
                        playerName[i] = playerName[i].trim();
                        playerMove[i] = playerMove[i].replaceAll("\"", "");
                        playerMove[i] = playerMove[i].trim();
                    }

                    if (playerName[1].equals(this.outPlayer)) {
                        System.out.println("printed out " + playerMove[1]);
                        accessToBuffers.getRemotePlayerBuffer().add(playerMove[1]);
                        System.out.println("buffer thread size: " + accessToBuffers.getRemotePlayerBuffer().size());
                        get.remove();
                    }

                    else if(playerName[1].equals(this.ourPlayer)){
                        get.remove();
                    }

                } else if (splitInfo[0].equals("SVR GAME YOURTURN")) {
                    accessToBuffers.getHomePlayerBuffer().add(splitInfo[0]);
                    get.remove();
                } else if (splitInfo[0].equals("SVR GAME LOSS")) {
                    accessToBuffers.setServerNetworkWinOrLoss(false);
                    get.remove();
                    runnning = false;
                } else if (splitInfo[0].equals("SVR GAME WIN")) {
                    accessToBuffers.setServerNetworkWinOrLoss(true);
                    get.remove();
                    runnning = false;
                }
            }
        }
    }
}

