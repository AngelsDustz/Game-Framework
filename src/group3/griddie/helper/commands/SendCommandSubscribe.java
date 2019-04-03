package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendCommandSubscribe implements Command {
    private NetworkRunner runner;
    private String game;

    public SendCommandSubscribe(NetworkRunner runner, String game){
        this.game = game;
        this.runner = runner;
    }

    @Override
    public void execute() {
        this.runner.putInBufferOut("subscribe " + this.game);
    }

    public void setGame(String game){
        this.game = game;
    }
}
