package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkMain;

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
