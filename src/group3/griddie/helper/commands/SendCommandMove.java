package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkMain;

public class SendCommandMove implements Command {
    //access for the whole class
    private NetworkMain runner;
    private int number;

    //initialize the class
    public SendCommandMove(NetworkMain runner, int number){
        this.number = number;
        this.runner = runner;
    }

    //execute the command
    @Override
    public void execute() {
        this.runner.putInBufferOut("move " + this.number);
    }

    //set the move number
    public void setMove(int number){
        this.number = number;
    }
}
