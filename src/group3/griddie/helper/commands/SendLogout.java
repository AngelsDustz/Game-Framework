package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendLogout implements Command {
    private NetworkRunner runner;

    public SendLogout(NetworkRunner runner){
        this.runner = runner;
    }

    @Override
    public void execute() {
        this.runner.putInBufferOut("logout");
    }
}
