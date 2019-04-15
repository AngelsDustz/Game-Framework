package group3.griddie.networkOLD.commands;

import group3.griddie.networkOLD.NetworkMain;

public class SendCommandSubscribe implements Command {
    //access for the whole class
    private NetworkMain runner;
    private String game;

    //initialize the class
    public SendCommandSubscribe(NetworkMain runner, String game){
        this.game = game;
        this.runner = runner;
    }

    //execute the command
    @Override
    public void execute() {
        this.runner.putInBufferOut("subscribe " + this.game);
    }

    //set the game string
    public void setGame(String game){
        this.game = game;
    }
}
