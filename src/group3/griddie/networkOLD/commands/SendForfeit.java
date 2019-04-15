package group3.griddie.networkOLD.commands;

import group3.griddie.networkOLD.NetworkMain;

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
