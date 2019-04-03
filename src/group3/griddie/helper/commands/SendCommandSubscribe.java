package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkMain;

public class SendCommandSubscribe implements Command {
    //access for the whole class
    private NetworkMain runner;
    private String game;

    //initialize the class
    public SendCommandSubscribe(NetworkMain runner, String game) {
        this.game = game;
        this.runner = runner;
    }

    //execute the command
    @Override
    public void execute() {
        this.runner.putInBufferOut(String.format("subscribe %s", this.game));
    }

    //set the game string
    public void setGame(String game) {
        this.game = game;
    }
}
