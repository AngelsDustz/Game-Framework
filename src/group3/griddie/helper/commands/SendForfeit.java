package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendForfeit implements Command {
    //access for the whole class
    private NetworkRunner runner;

    //initialize the class
    public SendForfeit(NetworkRunner runner){
        this.runner = runner;
    }

    //execute the command
    @Override
    public void execute() {
        this.runner.putInBufferOut("forfeit");
    }
}
