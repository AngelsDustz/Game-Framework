package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class GetInformation implements Command {
    //access to the class
    private String returnString;
    private NetworkRunner runner;

    //initialize the class
    public GetInformation(NetworkRunner runner){
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
