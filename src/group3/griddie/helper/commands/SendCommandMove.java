package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendCommandMove implements Command {
    private NetworkRunner runner;
    private int number;

    public SendCommandMove(NetworkRunner runner, int number){
        this.number = number;
        this.runner = runner;
    }

    @Override
    public void execute() {
        this.runner.putInBufferOut("move " + this.number);
    }

    public void setMove(int number){
        this.number = number;
    }
}
