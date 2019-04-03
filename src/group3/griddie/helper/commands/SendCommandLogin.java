package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendCommandLogin implements Command {

    private NetworkRunner runner;
    private String name;

    public SendCommandLogin(NetworkRunner runner, String name){
        this.name = name;
        this.runner = runner;
    }

    @Override
    public void execute(){
        this.runner.putInBufferOut("login " + this.name);
    }

    public void setName(String name){
        this.name = name;
    }
}
