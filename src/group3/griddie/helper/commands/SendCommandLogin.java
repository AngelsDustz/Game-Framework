package group3.griddie.helper.commands;

import group3.griddie.helper.NetworkRunner;

public class SendCommandLogin implements Command {
    //access for the whole class
    private NetworkRunner runner;
    private String name;

    //initialize the class
    public SendCommandLogin(NetworkRunner runner, String name) {
        this.name = name;
        this.runner = runner;
    }

    //execute the command
    @Override
    public void execute() {
        this.runner.putInBufferOut(String.format("login %s", this.name));
    }

    //set the name for login
    public void setName(String name) {
        this.name = name;
    }
}
