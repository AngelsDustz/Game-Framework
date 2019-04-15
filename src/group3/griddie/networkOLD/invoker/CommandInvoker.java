package group3.griddie.networkOLD.invoker;

import group3.griddie.networkOLD.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    //list of commands in case of redo's
    private final List<Command> commandOperations = new ArrayList<>();

    public void executeCommand(Command command){
        commandOperations.add(command);
        command.execute();
    }
}
