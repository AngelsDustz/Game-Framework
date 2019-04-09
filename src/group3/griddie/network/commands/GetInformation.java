package group3.griddie.network.commands;

import group3.griddie.network.NetworkMain;

public class GetInformation implements Command {
    //access to the class
    private String returnString;
    private NetworkMain runner;

    //initialize the class
    public GetInformation(NetworkMain runner){
        this.returnString = new String();
        this.runner = runner;
    }
    //execute method
    @Override
    public void execute() {
        this.returnString = this.runner.readBufferIn();
    }

    //return the String 
    public String getReturnString(){
        return this.returnString;
    }
}
