package group3.griddie.network.commands;

import group3.griddie.network.NetworkMain;

public class SendForfeit implements Command {
    //access for the whole class
    private NetworkMain runner;

    //initialize the class
    public SendForfeit(NetworkMain runner){
        this.runner = runner;
    }

    //execute the command
    @Override
    public void execute() {
        this.runner.putInBufferOut("forfeit");
    }
}
