package group3.griddie.network.commands;

import group3.griddie.network.NetworkMain;

import java.util.LinkedList;

public class GetAllInformation implements Command {
    //access to the class
    private LinkedList<String> returnList;
    private NetworkMain runner;

    //initialize the class
    public GetAllInformation(NetworkMain runner){
        this.returnList = new LinkedList<>();
        this.runner = runner;
    }

    //execute the class and get the linkedlist
    @Override
    public void execute() {
        this.returnList = this.runner.printAll();
    }

    //get the linkedList from command
    public LinkedList<String> getCommandInformation(){
        return this.returnList;
    }
}
