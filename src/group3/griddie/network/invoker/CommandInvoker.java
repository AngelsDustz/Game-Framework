package group3.griddie.network.invoker;

import group3.griddie.network.commands.Command;

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
