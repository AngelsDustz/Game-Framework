package group3.griddie.networkOLD.networktranslator;

public class NetworkTranslator {

    public static int translateMove(int start, String game, int x, int y){
        int move = 0;
        switch (game){
            case "tic-tac-toe":
                if(start == 0){
                    move = x + y * 3;
                }

                else if (start == 1){
                    move = x + y * 3 - 1;
                }
                break;
            case "revisie":
                if(start == 0){
                    move = x + y * 8;
                }

                else if (start == 1){
                    move = x + y * 8 - 1;
                }
                break;
        }

        return move;
    }
}