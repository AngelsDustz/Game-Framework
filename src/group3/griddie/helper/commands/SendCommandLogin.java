package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkMain;

public class SendCommandLogin implements Command {
    //access for the whole class
    private NetworkMain runner;
    private String name;

    //initialize the class
    public SendCommandLogin(NetworkMain runner, String name){
        this.name = name;
        this.runner = runner;
    }

    //execute the command
    @Override
    public void execute(){
        this.runner.putInBufferOut("login " + this.name);
    }

    //set the name for login
    public void setName(String name){
        this.name = name;
    }
}
