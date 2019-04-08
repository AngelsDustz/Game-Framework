package group3.griddie.network.commands;

import group3.griddie.network.NetworkMain;

public class SendLogout implements Command {
    //access for the whole class
    private NetworkMain runner;

    //initialize the class
    public SendLogout(NetworkMain runner){
        this.runner = runner;
    }

    //execute the command
    @Override
    public void execute() {
        this.runner.putInBufferOut("logout");
    }
}
