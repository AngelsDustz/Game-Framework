package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendCommandSubscribe implements Command {
    //access for the whole class
    private NetworkRunner runner;
    private String game;

    //initialize the class
    public SendCommandSubscribe(NetworkRunner runner, String game){
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
