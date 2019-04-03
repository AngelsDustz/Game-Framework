package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendForfeit implements Command {
    private NetworkRunner runner;

    public SendForfeit(NetworkRunner runner){
        this.runner = runner;
    }

    @Override
    public void execute() {
        this.runner.putInBufferOut("forfeit");
    }
}
