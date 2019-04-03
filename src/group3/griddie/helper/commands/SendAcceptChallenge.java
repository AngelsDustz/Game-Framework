package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendAcceptChallenge implements Command {
    private NetworkRunner runner;

    public SendAcceptChallenge(NetworkRunner runner){
        this.runner = runner;
    }

    @Override
    public void execute() {
        this.runner.putInBufferOut("challenge accept");
    }

}
