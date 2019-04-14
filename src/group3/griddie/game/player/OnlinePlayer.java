package group3.griddie.game.player;

import group3.griddie.game.server.Communication;

public class OnlinePlayer extends Player {

    private Communication communication;

    public OnlinePlayer(String name, Communication communication) {
        super(name);

        this.communication = communication;
    }

    @Override
    protected void onInit() {
        getGame().onMove.addListener(move -> {
            if (move.getPlayer() != this) {
                int num = move.getX() + (move.getY() * getGame().getBoard().getWidth());
                communication.sendMove(num);
            }
        });

        communication.moveReceivedEvent.addListener(move -> {
            if (isOnTurn() && move.getPlayer() == this) {
                getGame().playerMove(this, move.getX(), move.getY());
            }
        });
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




































//    public OnlinePlayer() {
////        thread = new Thread(this);
////
////        ServerResponse gameSR = new ServerResponse("GAME");
////        gameSR.addSub(new ServerResponse("MOVE", this::handleMove));
////        gameSR.addSub(new ServerResponse("YOURTURN", this::handleYourTurn));
////        gameSR.addSub(new ServerResponse("MATCH", this::handleMatch));
////
////        ServerResponse svrSR = new ServerResponse("SVR");
////        svrSR.addSub(gameSR);
////
////        root = new ServerResponse("");
////        root.addSub(svrSR);
//    }

//    @Override
//    public void run() {
//        while (true) {
//            String line = "";
//
//            try {
//                line = in.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            String[] words = line.split(" ");
//
//            ServerResponse current = root;
//            int index = 0;
//            for (String w : words) {
//                index++;
//                for (ServerResponse sr : current.getSubs()) {
//                    if (sr.getCommand().equals(w)) {
//                        current = sr;
//
//                        StringBuilder data = new StringBuilder();
//
//                        for (int i = index; i < words.length; i++) {
//                            data.append(words[i]).append(" ");
//                        }
//
//                        current.execute(data.toString());
//                    }
//                }
//            }
//        }
//    }

//    public void handleMatch(Map<String, String> data) {
//        this.setReady(true);
//    }
//
//    private void handleMove(Map<String, String> data) {
//        if (!data.get("PLAYER").equals(getName())) {
//            startTurn();
//        }
//
//        if (isOnTurn()) {
//            int move = Integer.parseInt(data.get("MOVE"));
//
//            System.out.println("Received move " + move);
//
//            int x = move % getGame().getBoard().getWidth();
//            int y = move / getGame().getBoard().getWidth();
//
//            getGame().playerMove(this, x, y);
//
//            endTurn();
//
//            this.getGame().getLobby().getPlayer(0).startTurn();
//        } else {
//            System.out.println("ERROR");
//            System.out.println(data);
//        }
//    }
//
//    private void handleYourTurn(Map<String, String> data) {
//
//    }
//
//    private void sendOpponentMove(int x, int y) {
//        int move = NetworkTranslator.translateMove(0, "tic-tac-toe", x, y);
//        out.println("MOVE " + move);
//    }

}
